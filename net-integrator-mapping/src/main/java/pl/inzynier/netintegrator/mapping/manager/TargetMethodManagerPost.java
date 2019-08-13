package pl.inzynier.netintegrator.mapping.manager;

import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import groovy.lang.GroovyShell;
import lombok.Builder;
import pl.inzynier.netintegrator.loadbalancer.LoadBalancerService;
import pl.inzynier.netintegrator.loadbalancer.dto.LoadBalancerIpInputData;
import pl.inzynier.netintegrator.loadbalancer.dto.LoadBalancerIpOutputData;
import pl.inzynier.netintegrator.mapping.HttpServletRequestUtil;
import pl.inzynier.netintegrator.mapping.dto.TargetEndpointDto;
import pl.inzynier.netintegrator.mapping.dto.UrlMappingDto;
import pl.inzynier.netintegrator.script.ScriptService;

@Builder
class TargetMethodManagerPost implements TargetMethodManager {

    GroovyShell groovyShell;
    RestTemplate restTemplate;
    ScriptService scriptService;
    LoadBalancerService loadBalancerService;

    @Override
    public ResponseEntity<String> manage(UrlMappingDto urlMapping, HttpServletRequest request) {

        try {
            Long urlMappingId = urlMapping.getUrlMappingId();
            Map<String, String> headerAsMap = HttpServletRequestUtil.getHeaderAsMap(request);

            LoadBalancerIpInputData build = LoadBalancerIpInputData.builder()
                    .urlMappingId(urlMappingId)
                    .requestHeader(headerAsMap)
                    .build();
            LoadBalancerIpOutputData hostIp = loadBalancerService.getHostIp(build);
            String addressIp = hostIp.getAddressIp();

            TargetEndpointDto target = urlMapping.getTarget();
            String fullUrl = target.getFullUrl(addressIp);

            // wykonaj skrypty na danych z body
            String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            body = scriptService.executeScripts(urlMappingId, body);


            return restTemplate.postForEntity(fullUrl, body, String.class);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
