package pl.inzynier.netintegrator.mapping.manager.generator;

import org.springframework.stereotype.Component;

import pl.inzynier.netintegrator.mapping.dto.UrlMappingDto;

@Component
class HttpMethodMapKeyGeneratorImpl implements HttpMethodMapKeyGenerator {

    @Override
    public String genreate(UrlMappingDto urlMapping) {
        String publish = urlMapping.getEndpoint().getMethod().toString();
        String targetMethod = urlMapping.getTarget().getMethod().toString();
        return publish + HttpMethodMapKeys.CONNECTOR + targetMethod;
    }
}
