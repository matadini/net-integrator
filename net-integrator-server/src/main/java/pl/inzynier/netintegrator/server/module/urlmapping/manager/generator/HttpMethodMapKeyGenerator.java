package pl.inzynier.netintegrator.server.module.urlmapping.manager.generator;

import org.springframework.stereotype.Component;
import pl.inzynier.netintegrator.server.module.urlmapping.dto.UrlMappingDto;

@Component
public interface HttpMethodMapKeyGenerator {

    String genreate(UrlMappingDto urlMapping);
}
