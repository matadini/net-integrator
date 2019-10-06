package pl.inzynier.netintegrator.server.httpmethod.mapping;

import com.google.common.collect.Maps;
import groovy.lang.GroovyShell;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.http.util.HttpServletRequestUtil;
import pl.inzynier.netintegrator.loadbalancer.LoadBalancerService;
import pl.inzynier.netintegrator.loadbalancer.dto.LoadBalancerIpOutputData;
import pl.inzynier.netintegrator.mapping.dto.TargetEndpointDto;
import pl.inzynier.netintegrator.mapping.dto.UrlMappingReadDto;
import pl.inzynier.netintegrator.script.ScriptService;
import spark.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
class TargetMethodManagerPost implements TargetMethodManager {

    private final GroovyShell groovyShell;
    // private final RestTemplate restTemplate;
    private final ScriptService scriptService;
    private final LoadBalancerService loadBalancerService;

    @Override
    public Object manage(UrlMappingReadDto urlMapping, HttpServletRequest request, HttpServletResponse response) throws TargetMethodManagerException {
        try {

            // pobierz adres dla mappingu
            Long urlMappingId = urlMapping.getUrlMappingId();
            LoadBalancerIpOutputData hostIp = loadBalancerService.getHostIp(urlMappingId);
            String addressIp = hostIp.getAddressIp();

            // wykonaj skrypty na danych z body
            String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            String bodyAfterScript = scriptService.executeScripts(urlMappingId, body);

            // wykonaj zapytanie do serwera targetowego
            TargetEndpointDto target = urlMapping.getTarget();
            String fullUrl = target.getFullUrl(addressIp);


            //return restTemplate.postForEntity(fullUrl, body, String.class);

        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            String message = e.getMessage();
            try {
                PrintWriter writer = response.getWriter();
                writer.write(message);
                writer.flush();
                writer.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        return response;
    }
}

