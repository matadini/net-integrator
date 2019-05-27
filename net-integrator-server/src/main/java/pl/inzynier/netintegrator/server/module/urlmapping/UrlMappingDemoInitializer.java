package pl.inzynier.netintegrator.server.module.urlmapping;

import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;

/**
 * Inicjalizuje testowe dane w tabeli konfiguracji mapowan
 */
@Value
@Component
class UrlMappingDemoInitializer {

    UrlMappingRepository mappingRepository;

    @Autowired
    public UrlMappingDemoInitializer(UrlMappingRepository mappingRepository) {
        this.mappingRepository = mappingRepository;
    }

    @PostConstruct
    private void initializeDemo() {

        // 0
        PublishEndpoint publishEndpoint0 = new PublishEndpoint(
                "/api-get",
                RequestMethod.GET);

        TargetEndpoint targetEndpoint0 = new TargetEndpoint(
                "/fake-get",
                "http://localhost:9090",
                RequestMethod.GET);

        UrlMapping mapping0 = new UrlMapping(publishEndpoint0, targetEndpoint0);
        mappingRepository.save(mapping0);

        // 2
        PublishEndpoint publishEndpoint2 = new PublishEndpoint(
                "/api-post",
                RequestMethod.POST);

        TargetEndpoint targetEndpoint2 = new TargetEndpoint(
                "/fake-post",
                "http://localhost:9090",
                RequestMethod.POST);

        UrlMapping mapping2 = new UrlMapping(publishEndpoint2, targetEndpoint2);
        mappingRepository.save(mapping2);
        // przetestuj kontrolnie

    }
}
