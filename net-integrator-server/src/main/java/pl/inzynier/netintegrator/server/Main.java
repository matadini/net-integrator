package pl.inzynier.netintegrator.server;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.pmw.tinylog.Logger;
import pl.inzynier.netintegrator.db.util.DatabaseConfiguration;
import pl.inzynier.netintegrator.http.util.RequestMethod;
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
import spark.Service;

import java.util.List;


@Data
class NetIntegratorServerConfig {
    Integer port;
}

@RequiredArgsConstructor
class NetIntegratorServer {

    private Service service;
    private final NetIntegratorServerConfig config;

    void run() {
        service = Service.ignite().port(config.port);
        service.get("/test", (req,resp)->{
            return "Hello test!";
        });
    }
}


class DemoInitializer {
    public void demoInit() {

    }
}

public class Main {

    public static void main(String[] args) throws UrlMappingServiceException, ScriptServiceException {

        NetIntegratorServerConfig config = new NetIntegratorServerConfig();
        config.setPort(9090);

        NetIntegratorServer netIntegratorServer = new NetIntegratorServer(config);
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
