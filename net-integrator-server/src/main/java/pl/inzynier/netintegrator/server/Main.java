package pl.inzynier.netintegrator.server;

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
import pl.inzynier.netintegrator.server.configuration.Configuration;
import pl.inzynier.netintegrator.server.configuration.ConfigurationRepository;

import java.util.List;

public class Main {

    public static void main(String[] args) throws UrlMappingServiceException {




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
