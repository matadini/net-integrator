package pl.inzynier.netintegrator.server;

import lombok.RequiredArgsConstructor;
import org.pmw.tinylog.Logger;
import pl.inzynier.netintegrator.http.util.RequestMethod;
import pl.inzynier.netintegrator.mapping.UrlMappingService;
import pl.inzynier.netintegrator.mapping.dto.PublishEndpointDto;
import pl.inzynier.netintegrator.mapping.dto.TargetEndpointDto;
import pl.inzynier.netintegrator.mapping.dto.UrlMappingWriteDto;
import pl.inzynier.netintegrator.script.ExampleGroovyScript;
import pl.inzynier.netintegrator.script.ScriptService;
import pl.inzynier.netintegrator.script.dto.ScriptType;
import pl.inzynier.netintegrator.script.dto.ScriptWriteDto;

@RequiredArgsConstructor
class DemoInitializer {

    private final ScriptService scriptService;
    private final UrlMappingService urlMappingService;

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

// internetowe mappowanie
//            PublishEndpointDto publishEndpoint3 = new PublishEndpointDto("/internet", RequestMethod.GET);
//            TargetEndpointDto targetEndpoint3 = new TargetEndpointDto("/posts/1", RequestMethod.GET,
//                    "http://jsonplaceholder.typicode.com");
//
//            UrlMappingWriteDto mapping3 = new UrlMappingWriteDto(publishEndpoint3, targetEndpoint3);
//            Long save3 = urlMappingService.create(mapping3);

            // skrypty do mapowan
            ScriptWriteDto script1 = new ScriptWriteDto();
            script1.setUrlMappingId(save1);
            script1.setContent(ExampleGroovyScript.createExampleGroovyScriptJsonToXml());
            script1.setScriptType(ScriptType.POST_CALL);

            ScriptWriteDto script2 = new ScriptWriteDto();
            script2.setUrlMappingId(save2);
            script2.setContent(ExampleGroovyScript.createExampleGroovyScriptToUpperCase());
            script2.setScriptType(ScriptType.POST_CALL);

            scriptService.addScript(script1);
            scriptService.addScript(script2);

        } catch (Exception e) {
            Logger.info(e);
        }
    }
}
