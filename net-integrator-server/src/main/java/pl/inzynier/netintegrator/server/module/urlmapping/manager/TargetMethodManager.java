package pl.inzynier.netintegrator.server.module.urlmapping.manager;

import groovy.lang.GroovyShell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.inzynier.netintegrator.loadbalancer.LoadBalancerService;
import pl.inzynier.netintegrator.server.module.urlmapping.dto.UrlMappingDto;
import pl.inzynier.netintegrator.server.module.urlmapping.manager.generator.HttpMethodMapKeys;

import javax.servlet.http.HttpServletRequest;

public interface TargetMethodManager {
    ResponseEntity<String> manage(UrlMappingDto urlMapping, HttpServletRequest request) throws TargetMethodManagerException;
}



