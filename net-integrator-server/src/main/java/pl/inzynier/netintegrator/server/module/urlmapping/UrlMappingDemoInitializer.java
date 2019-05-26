package pl.inzynier.netintegrator.server.module.urlmapping;

import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.PostConstruct;
import java.util.Optional;

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
                "/dodano",
                RequestMethod.GET);

        TargetEndpoint targetEndpoint0 = new TargetEndpoint(
                "/test",
                "http://localhost:9090",
                RequestMethod.GET);

        UrlMapping mapping0 = new UrlMapping();
        mapping0.setTarget(targetEndpoint0);
        mapping0.setEndpoint(publishEndpoint0);
        mappingRepository.save(mapping0);

        // 1
        PublishEndpoint publishEndpoint1 = new PublishEndpoint(
                "/odczytano",
                RequestMethod.GET);

        TargetEndpoint targetEndpoint1 = new TargetEndpoint(
                "/odczytano-target",
                "http://localhost:9090",
                RequestMethod.GET);

        UrlMapping mapping1 = new UrlMapping();
        mapping1.setTarget(targetEndpoint1);
        mapping1.setEndpoint(publishEndpoint1);
        mappingRepository.save(mapping1);


        // przetestuj kontrolnie

    }
}
