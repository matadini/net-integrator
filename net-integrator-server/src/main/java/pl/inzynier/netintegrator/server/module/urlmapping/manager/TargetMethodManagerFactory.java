package pl.inzynier.netintegrator.server.module.urlmapping.manager;

import groovy.lang.GroovyShell;
import lombok.NoArgsConstructor;
import org.springframework.web.client.RestTemplate;
import pl.inzynier.netintegrator.server.module.script.ScriptService;

@NoArgsConstructor
public class TargetMethodManagerFactory {

    public static TargetMethodManager getToGet(RestTemplate restTemplate) {
        return new TargetMethodManagerGet(restTemplate);
    }

    public static TargetMethodManager postToPost(RestTemplate restTemplate,
                                                 ScriptService scriptService,
                                                 GroovyShell groovyShell) {
        return new TargetMethodManagerGroovyPost(restTemplate, scriptService, groovyShell);
    }
}
