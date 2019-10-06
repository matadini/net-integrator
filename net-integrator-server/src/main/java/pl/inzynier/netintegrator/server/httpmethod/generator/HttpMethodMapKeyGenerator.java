package pl.inzynier.netintegrator.server.httpmethod.generator;

import pl.inzynier.netintegrator.mapping.dto.UrlMappingReadDto;


public interface HttpMethodMapKeyGenerator {

    String genreate(UrlMappingReadDto urlMapping);

    static HttpMethodMapKeyGenerator create() {
        return new HttpMethodMapKeyGeneratorImpl();
    }
}