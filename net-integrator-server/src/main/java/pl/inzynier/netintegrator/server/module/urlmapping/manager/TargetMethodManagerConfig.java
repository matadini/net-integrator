package pl.inzynier.netintegrator.server.module.urlmapping.manager;

import groovy.lang.GroovyShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.inzynier.netintegrator.loadbalancer.LoadBalancerService;
import pl.inzynier.netintegrator.server.module.script.ScriptService;
import pl.inzynier.netintegrator.server.module.urlmapping.manager.generator.HttpMethodMapKeys;

@Configuration
class TargetMethodManagerConfig {

    RestTemplate restTemplate;
    GroovyShell groovyShell;
    ScriptService scriptService;
    LoadBalancerService loadBalancerService;

    @Autowired
    public TargetMethodManagerConfig(RestTemplate restTemplate, GroovyShell groovyShell, ScriptService scriptService, LoadBalancerService loadBalancerService) {
        this.restTemplate = restTemplate;
        this.groovyShell = groovyShell;
        this.scriptService = scriptService;
        this.loadBalancerService = loadBalancerService;
    }

    @Bean(name = HttpMethodMapKeys.GET_TO_GET)
    TargetMethodManager getToGet() {
        return TargetMethodManagerFactory.getToGet(restTemplate, loadBalancerService);
    }

    @Bean(name = HttpMethodMapKeys.POST_TO_POST)
    TargetMethodManager postToPost() {
        return TargetMethodManagerFactory.postToPost(restTemplate, scriptService, groovyShell, loadBalancerService);
    }

}
