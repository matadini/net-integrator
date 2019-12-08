package pl.inzynier.netintegrator.server.httpmethod.generator;

import pl.inzynier.netintegrator.mapping.core.dto.UrlMappingReadDto;


class HttpMethodMapKeyGeneratorImpl implements HttpMethodMapKeyGenerator {

    @Override
    public String genreate(UrlMappingReadDto urlMapping) {
        String publish = urlMapping.getEndpoint().getMethod().toString();
        String targetMethod = urlMapping.getTarget().getMethod().toString();
        return publish + HttpMethodMapKeys.CONNECTOR + targetMethod;
    }
}
