package pl.inzynier.netintegrator.server;

import lombok.RequiredArgsConstructor;
import org.pmw.tinylog.Logger;
import pl.inzynier.netintegrator.http.util.RequestMethod;
import pl.inzynier.netintegrator.mapping.core.UrlMappingService;
import pl.inzynier.netintegrator.mapping.core.dto.PublishEndpointDto;
import pl.inzynier.netintegrator.mapping.core.dto.TargetEndpointDto;
import pl.inzynier.netintegrator.mapping.core.dto.UrlMappingWriteDto;
import pl.inzynier.netintegrator.script.ExampleGroovyScript;
import pl.inzynier.netintegrator.script.core.ScriptService;
import pl.inzynier.netintegrator.script.core.dto.ScriptType;
import pl.inzynier.netintegrator.script.core.dto.ScriptWriteDto;
import pl.inzynier.netintegrator.user.core.UserService;
import pl.inzynier.netintegrator.user.core.dto.UserWriteDTO;

/**
 * Wstawia do bazy przykladowe dane
 */
@RequiredArgsConstructor
class DemoInitializer {

    private final ScriptService scriptService;
    private final UrlMappingService urlMappingService;
    private final UserService userService;

    public void demoInit() {
        try {

            // 0 - czyste mapowanie
            PublishEndpointDto publishEndpoint0 = new PublishEndpointDto("/api-get", RequestMethod.GET);
            TargetEndpointDto targetEndpoint0 = new TargetEndpointDto("/fake-get", RequestMethod.GET, "http://localhost:9090");
            UrlMappingWriteDto mapping0 = new UrlMappingWriteDto(publishEndpoint0, targetEndpoint0);
            Long save0 = urlMappingService.create(mapping0);


            // 1 - mapowanie ze skryptem json to xml
            PublishEndpointDto publishEndpoint1 = new PublishEndpointDto("/api-post-xml", RequestMethod.POST);
            TargetEndpointDto targetEndpoint1 = new TargetEndpointDto("/fake-post", RequestMethod.POST, "http://localhost:9090");
            UrlMappingWriteDto mapping1 = new UrlMappingWriteDto(publishEndpoint1, targetEndpoint1);
            Long save1 = urlMappingService.create(mapping1);


            // 2 - mapowanie ze skryptem
            PublishEndpointDto publishEndpoint2 = new PublishEndpointDto("/api-post", RequestMethod.POST);
            TargetEndpointDto targetEndpoint2 = new TargetEndpointDto("/fake-post", RequestMethod.POST, "http://localhost:9090");
            UrlMappingWriteDto mapping2 = new UrlMappingWriteDto(publishEndpoint2, targetEndpoint2);
            Long save2 = urlMappingService.create(mapping2);

            // skrypty do mapowan
            ScriptWriteDto script1 = new ScriptWriteDto();
            script1.setUrlMappingId(save1);
            script1.setContent(ExampleGroovyScript.createExampleGroovyScriptJsonToXml());
            script1.setScriptType(ScriptType.POST_CALL);

            ScriptWriteDto script2 = new ScriptWriteDto();
            script2.setUrlMappingId(save2);
            script2.setContent(ExampleGroovyScript.createExampleGroovyScriptToUpperCase());
            script2.setScriptType(ScriptType.POST_CALL);

            scriptService.create(script1);
            scriptService.create(script2);

            // mapowanie - internet 1
            PublishEndpointDto publishEndpoint3 = new PublishEndpointDto("/nowy-mapping", RequestMethod.GET);
            TargetEndpointDto targetEndpoint3 = new TargetEndpointDto("/posts/1", RequestMethod.GET, "http://jsonplaceholder.typicode.com");
            UrlMappingWriteDto mapping3 = new UrlMappingWriteDto(publishEndpoint3, targetEndpoint3);
            Long save3 = urlMappingService.create(mapping3);


            // mapowanie - internet 2
            PublishEndpointDto publishEndpoint4 = new PublishEndpointDto("/nowy-mapping-xml", RequestMethod.GET);
            TargetEndpointDto targetEndpoint4 = new TargetEndpointDto("/posts/1", RequestMethod.GET, "http://jsonplaceholder.typicode.com");
            UrlMappingWriteDto mapping4 = new UrlMappingWriteDto(publishEndpoint4, targetEndpoint4);
            Long save4 = urlMappingService.create(mapping4);

            ScriptWriteDto script3 = new ScriptWriteDto();
            script3.setUrlMappingId(save4);
            script3.setContent(ExampleGroovyScript.toUpperCaseOnly());
            script3.setScriptType(ScriptType.POST_CALL);
            scriptService.create(script3);

            userService.create(new UserWriteDTO("admin", "admin"));

        } catch (Exception e) {
            Logger.info(e);
        }
    }

}
