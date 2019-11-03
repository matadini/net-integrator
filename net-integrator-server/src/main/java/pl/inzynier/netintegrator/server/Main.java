package pl.inzynier.netintegrator.server;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.pmw.tinylog.Logger;
import pl.inzynier.netintegrator.db.util.DatabaseConfiguration;
import pl.inzynier.netintegrator.db.util.EntityManagerFactoryProvider;
import pl.inzynier.netintegrator.http.spark.SparkController;
import pl.inzynier.netintegrator.http.util.RequestMethod;
import pl.inzynier.netintegrator.mapping.UrlMappingEntityManagerFactoryProviders;
import pl.inzynier.netintegrator.mapping.UrlMappingService;
import pl.inzynier.netintegrator.mapping.UrlMappingServiceFactory;
import pl.inzynier.netintegrator.mapping.dto.*;
import pl.inzynier.netintegrator.script.*;
import pl.inzynier.netintegrator.script.dto.ScriptReadDto;
import pl.inzynier.netintegrator.script.dto.ScriptServiceException;
import pl.inzynier.netintegrator.script.dto.ScriptType;
import pl.inzynier.netintegrator.script.dto.ScriptWriteDto;
import pl.inzynier.netintegrator.server.configuration.Configuration;
import pl.inzynier.netintegrator.server.configuration.ConfigurationRepository;
import pl.inzynier.netintegrator.server.httpmethod.generator.HttpMethodMapKeyGenerator;
import pl.inzynier.netintegrator.server.httpmethod.mapping.TargetMethodManager;
import pl.inzynier.netintegrator.server.httpmethod.mapping.TargetMethodManagerFactory;
import pl.inzynier.netintegrator.server.server.core.NetIntegratorContext;
import pl.inzynier.netintegrator.server.server.core.NetIntegratorServerImpl;
import pl.inzynier.netintegrator.server.server.core.NetIntegratorServerConfig;
import pl.inzynier.netintegrator.server.server.core.NetIntegratorServerCoreRoute;
import pl.inzynier.netintegrator.server.server.controller.urlmapping.UrlMappingController;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Main {

    public static void main(String[] args) throws UrlMappingServiceException, ScriptServiceException {
        runServer();
    }

    private static void runServer() {

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
        EntityManagerFactoryProvider scriptProvider = ScriptEntityManagerFactoryProviders.providerPostgres(configuration);
        ScriptService scriptService = ScriptServiceFactory.create(scriptProvider);

        EntityManagerFactoryProvider urlMappingProvider = UrlMappingEntityManagerFactoryProviders.providerPostgres(configuration);
        UrlMappingService urlMappingService = UrlMappingServiceFactory.create(urlMappingProvider);

        // Dane dla dema
        DemoInitializer demoInitializer = new DemoInitializer(scriptService, urlMappingService);
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
