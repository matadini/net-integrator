package pl.inzynier.netintegrator.server.module.urlmapping;

import org.springframework.stereotype.Component;

@Component
class HttpMethodMapKeyGeneratorImpl implements HttpMethodMapKeyGenerator {

    @Override
    public String genreate(UrlMapping urlMapping) {
        String publish = urlMapping.getEndpoint().getMethod().toString();
        String targetMethod = urlMapping.getTarget().getMethod().toString();
        return publish + HttpMethodMapKeys.CONNECTOR + targetMethod;
    }
}
