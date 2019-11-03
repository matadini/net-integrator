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

        // Serwisy modulow
        EntityManagerFactoryProvider scriptProvider = ScriptEntityManagerFactoryProviders.providerH2();
        ScriptService scriptService = ScriptServiceFactory.create(scriptProvider);

        EntityManagerFactoryProvider urlMappingProvider = UrlMappingEntityManagerFactoryProviders.providerH2();
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

    private static void urlMappingWithScript() throws UrlMappingServiceException, ScriptServiceException {
        // dodaj mapping
        PublishEndpointDto publishEndpoint2 = new PublishEndpointDto("/api-post", RequestMethod.POST);
        TargetEndpointDto targetEndpoint2 = new TargetEndpointDto("/fake-post", RequestMethod.POST, "http://localhost:9090");

        UrlMappingWriteDto mapping2 = new UrlMappingWriteDto(publishEndpoint2, targetEndpoint2);
        UrlMappingService urlMappingService = UrlMappingServiceFactory.create(null);
        Long aLong = urlMappingService.create(mapping2);
        List<UrlMappingReadDto> mappings = urlMappingService.findAll();
        mappings.forEach(System.out::println);

        // dodaj skrypt do mappingu
        ScriptWriteDto scriptWriteDto = new ScriptWriteDto();
        scriptWriteDto.setContent(ExampleGroovyScript.createExampleGroovyScriptToUpperCase());
        scriptWriteDto.setScriptType(ScriptType.POST_CALL);
        scriptWriteDto.setUrlMappingId(aLong);

        ScriptService scriptService = ScriptServiceFactory.create(null);
        scriptService.addScript(scriptWriteDto);

        List<ScriptReadDto> scripts = scriptService.findAll();
        scripts.forEach(System.out::println);
    }

    private static void urlMappingAdd() throws UrlMappingServiceException {
        // urlmapping dodawanie

        PublishEndpointDto publishEndpoint0 = new PublishEndpointDto("/api-get", RequestMethod.GET);
        TargetEndpointDto targetEndpoint0 = new TargetEndpointDto("/fake-get", RequestMethod.GET, "http://localhost:9090");
        UrlMappingWriteDto mapping0 = new UrlMappingWriteDto(publishEndpoint0, targetEndpoint0);


        UrlMappingService urlMappingService = UrlMappingServiceFactory.create(null);
        urlMappingService.create(mapping0);


        List<UrlMappingReadDto> all = urlMappingService.findAll();
        all.forEach(System.out::println);
    }

    private static void configurationTest() {
        DatabaseConfiguration mappingDbConfig = new DatabaseConfiguration();
        mappingDbConfig.setAddress("localhost-1");
        mappingDbConfig.setUser("user-2");
        mappingDbConfig.setPassword("password-3");

        Configuration configuration = new Configuration();
        configuration.setMappingDatabaseConfig(mappingDbConfig);

        ConfigurationRepository repository = ConfigurationRepository.create("D:/test/config.json");
        repository.createOrUpdate(configuration);

        Configuration read = repository.read();
        Logger.info(read);
    }
}
