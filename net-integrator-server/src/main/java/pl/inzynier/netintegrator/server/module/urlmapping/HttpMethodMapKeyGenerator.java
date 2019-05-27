package pl.inzynier.netintegrator.server.module.urlmapping;

import org.springframework.stereotype.Component;

@Component
interface HttpMethodMapKeyGenerator {

    String genreate(UrlMapping urlMapping);
}
