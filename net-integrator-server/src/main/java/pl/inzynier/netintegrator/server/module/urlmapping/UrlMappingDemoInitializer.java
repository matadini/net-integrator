package pl.inzynier.netintegrator.server.module.urlmapping;

import org.springframework.stereotype.Component;

import pl.inzynier.netintegrator.server.module.script.dto.ScriptServiceException;

import javax.annotation.PostConstruct;


@Component
interface UrlMappingDemoInitializer {
    @PostConstruct void initializeDemo() throws ScriptServiceException;
}
