package pl.inzynier.netintegrator.server.module.urlmapping.manager;

import org.springframework.web.client.RestTemplate;

import groovy.lang.GroovyShell;
import lombok.NoArgsConstructor;
import pl.inzynier.netintegrator.loadbalancer.LoadBalancerService;
import pl.inzynier.netintegrator.script.ScriptService;

@NoArgsConstructor
public class TargetMethodManagerFactory {

    public static TargetMethodManager getToGet(RestTemplate restTemplate, LoadBalancerService loadBalancerService) {
        return TargetMethodManagerGet.builder()
                .restTemplate(restTemplate)
                .loadBalancerService(loadBalancerService)
                .build();
    }

    public static TargetMethodManager postToPost(RestTemplate restTemplate,
                                                 ScriptService scriptService,
                                                 GroovyShell groovyShell,
                                                 LoadBalancerService loadBalancerService) {

        return TargetMethodManagerPost.builder()
                .groovyShell(groovyShell)
                .restTemplate(restTemplate)
                .scriptService(scriptService)
                .loadBalancerService(loadBalancerService)
                .build();
    }
}
