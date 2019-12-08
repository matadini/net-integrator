package pl.inzynier.netintegrator.server;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pl.inzynier.netintegrator.db.util.DatabaseConfiguration;
import pl.inzynier.netintegrator.db.util.EntityManagerFactoryProvider;
import pl.inzynier.netintegrator.http.spark.SparkController;
import pl.inzynier.netintegrator.mapping.entitymanagerprovider.UrlMappingEntityManagerFactoryProviders;
import pl.inzynier.netintegrator.mapping.UrlMappingService;
import pl.inzynier.netintegrator.mapping.UrlMappingServiceFactory;
import pl.inzynier.netintegrator.mapping.dto.*;
import pl.inzynier.netintegrator.script.*;
import pl.inzynier.netintegrator.script.dto.ScriptServiceException;
import pl.inzynier.netintegrator.server.configuration.Configuration;
import pl.inzynier.netintegrator.server.configuration.ConfigurationRepository;
import pl.inzynier.netintegrator.server.httpmethod.generator.HttpMethodMapKeyGenerator;
import pl.inzynier.netintegrator.server.httpmethod.mapping.TargetMethodManager;
import pl.inzynier.netintegrator.server.httpmethod.mapping.TargetMethodManagerFactory;
import pl.inzynier.netintegrator.server.server.core.*;
import pl.inzynier.netintegrator.server.server.controller.urlmapping.UrlMappingController;
import pl.inzynier.netintegrator.user.UserService;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws UrlMappingServiceException, ScriptServiceException {

        List<String> argList = Lists.newArrayList(args).stream()
                .filter(Objects::nonNull)
                .map(String::toUpperCase)
                .collect(Collectors.toList());


        // commonsy
        Gson gson = new GsonBuilder().serializeNulls().create();

        // jak plik z konfiguracja nie istnieje to
        // utworz go przypierwszym uruchomieniu
        String configFile = "config.json";
        if (!Files.exists(Paths.get(configFile))) {

            DatabaseConfiguration configuration = new DatabaseConfiguration();
            configuration.setUser("postgres");
            configuration.setPassword("admin");
            configuration.setAddress("localhost:5432");

            Configuration entity = new Configuration();
            entity.setDatabaseConfiguration(configuration);

            ConfigurationRepository configurationRepository = ConfigurationRepository.create(gson, configFile);
            configurationRepository.createOrUpdate(entity);
        }

        // odczytaj polaczenie do bazy z konfiguracji
        ConfigurationRepository configurationRepository = ConfigurationRepository.create(gson, configFile);
        Configuration read = configurationRepository.read();
        DatabaseConfiguration configuration = read.getDatabaseConfiguration();

        // Serwisy modulow
        boolean useH2 = argList.contains(NetIntegratorServerArgs.H2DB);
        System.out.println(useH2);

        EntityManagerFactoryProvider scriptProvider = useH2 ?
                ScriptEntityManagerFactoryProviders.providerH2() :
                ScriptEntityManagerFactoryProviders.providerPostgres(configuration);
        ScriptService scriptService = ScriptServiceFactory.create(scriptProvider);

        EntityManagerFactoryProvider urlMappingProvider = useH2 ?
                UrlMappingEntityManagerFactoryProviders.providerH2() :
                UrlMappingEntityManagerFactoryProviders.providerPostgres(configuration);
        UrlMappingService urlMappingService = UrlMappingServiceFactory.create(urlMappingProvider);

        // Dane dla dema
        UserService userService = null;
        DemoInitializer demoInitializer = new DemoInitializer(scriptService, urlMappingService, userService);
        demoInitializer.demoInit();

        // Uruchomienie serwera http i aplikacji wlasciwej
        NetIntegratorServerConfig config = new NetIntegratorServerConfig();
        config.setPort(8080);

        HttpMethodMapKeyGenerator httpMethodMapKeyGenerator = HttpMethodMapKeyGenerator.create();
        Map<String, TargetMethodManager> requestMethodManagerStrategyMap = TargetMethodManagerFactory.create(scriptService);

        List<SparkController> adminControllers = new ArrayList<>();
        adminControllers.add(new UrlMappingController(gson, urlMappingService));

        NetIntegratorServerCoreRoute route = new NetIntegratorServerCoreRoute(urlMappingService, httpMethodMapKeyGenerator, requestMethodManagerStrategyMap);
        NetIntegratorServerImpl netIntegratorServer = new NetIntegratorServerImpl(urlMappingService, config, route, adminControllers);
        netIntegratorServer.run();

        // instancje serwera wrzuc do kontekstu aby byl dostepny w kontrolerach administracyjnych
        NetIntegratorContext context = NetIntegratorContext.getContext();
        context.setNetIntegratorServer(netIntegratorServer);
    }

}
