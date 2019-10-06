package pl.inzynier.netintegrator.server;

import com.google.common.collect.Maps;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.pmw.tinylog.Logger;
import pl.inzynier.netintegrator.db.util.DatabaseConfiguration;
import pl.inzynier.netintegrator.http.util.RequestMethod;
import pl.inzynier.netintegrator.loadbalancer.LoadBalancerService;
import pl.inzynier.netintegrator.loadbalancer.LoadBalancerServiceFactory;
import pl.inzynier.netintegrator.mapping.UrlMappingService;
import pl.inzynier.netintegrator.mapping.UrlMappingServiceFactory;
import pl.inzynier.netintegrator.mapping.dto.UrlMappingServiceException;
import pl.inzynier.netintegrator.mapping.dto.PublishEndpointDto;
import pl.inzynier.netintegrator.mapping.dto.TargetEndpointDto;
import pl.inzynier.netintegrator.mapping.dto.UrlMappingReadDto;
import pl.inzynier.netintegrator.mapping.dto.UrlMappingWriteDto;
import pl.inzynier.netintegrator.script.ExampleGroovyScript;
import pl.inzynier.netintegrator.script.ScriptService;
import pl.inzynier.netintegrator.script.ScriptServiceFactory;
import pl.inzynier.netintegrator.script.dto.ScriptReadDto;
import pl.inzynier.netintegrator.script.dto.ScriptServiceException;
import pl.inzynier.netintegrator.script.dto.ScriptType;
import pl.inzynier.netintegrator.script.dto.ScriptWriteDto;
import pl.inzynier.netintegrator.server.configuration.Configuration;
import pl.inzynier.netintegrator.server.configuration.ConfigurationRepository;
import pl.inzynier.netintegrator.server.httpmethod.generator.HttpMethodMapKeyGenerator;
import pl.inzynier.netintegrator.server.httpmethod.generator.HttpMethodMapKeys;
import pl.inzynier.netintegrator.server.httpmethod.mapping.TargetMethodManager;
import pl.inzynier.netintegrator.server.httpmethod.mapping.TargetMethodManagerException;
import pl.inzynier.netintegrator.server.server.NetIntegratorServer;
import pl.inzynier.netintegrator.server.server.NetIntegratorServerConfig;
import pl.inzynier.netintegrator.server.server.NetIntegratorServerCoreRoute;
import spark.Response;
import spark.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


public class Main {

    public static void main(String[] args) throws UrlMappingServiceException, ScriptServiceException {

        // Serwisy modulow
        ScriptService scriptService = ScriptServiceFactory.create(null);
        UrlMappingService urlMappingService = UrlMappingServiceFactory.create(null);
        LoadBalancerService loadBalancerService = LoadBalancerServiceFactory.create(null);

        // Dane dla dema
        DemoInitializer demoInitializer = new DemoInitializer(scriptService, urlMappingService);
        demoInitializer.demoInit();

        // Uruchomienie serwera http i aplikacji wlasciwej
        NetIntegratorServerConfig config = new NetIntegratorServerConfig();
        config.setPort(8080);

        HttpMethodMapKeyGenerator httpMethodMapKeyGenerator = HttpMethodMapKeyGenerator.create();
        Map<String, TargetMethodManager> requestMethodManagerStrategyMap = TargetMethodManager.create(scriptService, loadBalancerService);

        NetIntegratorServerCoreRoute route = new NetIntegratorServerCoreRoute(urlMappingService, httpMethodMapKeyGenerator, requestMethodManagerStrategyMap);
        NetIntegratorServer netIntegratorServer = new NetIntegratorServer(scriptService, urlMappingService, config, route);
        netIntegratorServer.run();

    }

    private static void urlMappingWithScript() throws UrlMappingServiceException, ScriptServiceException {
        // dodaj mapping
        PublishEndpointDto publishEndpoint2 = new PublishEndpointDto("/api-post", RequestMethod.POST);
        TargetEndpointDto targetEndpoint2 = new TargetEndpointDto("/fake-post", RequestMethod.POST);

        UrlMappingWriteDto mapping2 = new UrlMappingWriteDto(publishEndpoint2, targetEndpoint2);
        UrlMappingService urlMappingService = UrlMappingServiceFactory.create(null);
        Long aLong = urlMappingService.addUrlMapping(mapping2);
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
        TargetEndpointDto targetEndpoint0 = new TargetEndpointDto("/fake-get", RequestMethod.GET);
        UrlMappingWriteDto mapping0 = new UrlMappingWriteDto(publishEndpoint0, targetEndpoint0);


        UrlMappingService urlMappingService = UrlMappingServiceFactory.create(null);
        urlMappingService.addUrlMapping(mapping0);


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
