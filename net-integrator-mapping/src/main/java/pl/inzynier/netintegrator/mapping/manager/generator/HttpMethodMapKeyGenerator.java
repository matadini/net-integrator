package pl.inzynier.netintegrator.mapping.manager.generator;

import org.springframework.stereotype.Component;

import pl.inzynier.netintegrator.mapping.dto.UrlMappingDto;

@Component
public interface HttpMethodMapKeyGenerator {

    String genreate(UrlMappingDto urlMapping);
}
