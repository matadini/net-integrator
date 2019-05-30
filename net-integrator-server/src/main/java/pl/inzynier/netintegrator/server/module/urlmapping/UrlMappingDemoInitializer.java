package pl.inzynier.netintegrator.server.module.urlmapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.inzynier.netintegrator.server.module.script.ScriptService;
import pl.inzynier.netintegrator.server.module.script.dto.ScriptServiceException;
import pl.inzynier.netintegrator.server.module.script.dto.ScriptType;

import javax.annotation.PostConstruct;


/**
 * Inicjalizuje testowe dane w tabeli konfiguracji mapowan
 */
@Component
class UrlMappingDemoInitializer {

    private final UrlMappingRepository mappingRepository;

    private final ScriptService scriptService;

    @Autowired
    public UrlMappingDemoInitializer(UrlMappingRepository mappingRepository, ScriptService scriptService) {
        this.mappingRepository = mappingRepository;
        this.scriptService = scriptService;
    }



    @PostConstruct
    private void initializeDemo() throws ScriptServiceException {

        // 0
        PublishEndpoint publishEndpoint0 = new PublishEndpoint(
                "/api-get",
                RequestMethod.GET);

        TargetEndpoint targetEndpoint0 = new TargetEndpoint(
                "/fake-get",
                RequestMethod.GET);

        UrlMapping mapping0 = new UrlMapping(publishEndpoint0, targetEndpoint0);
        mappingRepository.save(mapping0);

        // 1
        PublishEndpoint publishEndpoint1 = new PublishEndpoint(
                "/api-post-xml",
                RequestMethod.POST);

        TargetEndpoint targetEndpoint1 = new TargetEndpoint(
                "/fake-post",
                RequestMethod.POST);

        UrlMapping mapping1 = new UrlMapping(publishEndpoint1, targetEndpoint1);
        UrlMapping save1 = mappingRepository.save(mapping1);
        String scriptJsonToXml = ExampleGroovyScript.createExampleGroovyScriptJsonToXml();
        scriptService.addScript(save1.getUrlMappingId(), ScriptType.POST_CALL, scriptJsonToXml);


        // 2
        PublishEndpoint publishEndpoint2 = new PublishEndpoint(
                "/api-post",
                RequestMethod.POST);

        TargetEndpoint targetEndpoint2 = new TargetEndpoint(
                "/fake-post",
                RequestMethod.POST);

        UrlMapping mapping2 = new UrlMapping(publishEndpoint2, targetEndpoint2);
        UrlMapping save = mappingRepository.save(mapping2);
        String scriptJsonUpper = ExampleGroovyScript.createExampleGroovyScriptToUpperCase();
        scriptService.addScript(save.getUrlMappingId(), ScriptType.POST_CALL, scriptJsonUpper);


        // przetestuj kontrolnie

    }
}
