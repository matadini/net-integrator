package pl.inzynier.netintegrator.server.httpmethod.mapping;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import groovy.lang.GroovyShell;
import lombok.RequiredArgsConstructor;
import org.glassfish.jersey.client.ClientResponse;
import pl.inzynier.netintegrator.http.util.HttpServletRequestUtil;
import pl.inzynier.netintegrator.loadbalancer.LoadBalancerService;
import pl.inzynier.netintegrator.loadbalancer.dto.LoadBalancerIpOutputData;
import pl.inzynier.netintegrator.mapping.dto.TargetEndpointDto;
import pl.inzynier.netintegrator.mapping.dto.UrlMappingReadDto;
import pl.inzynier.netintegrator.script.ScriptService;
import spark.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
class TargetMethodManagerPost implements TargetMethodManager {

    // private final GroovyShell groovyShell;
    private final Gson gson;
    private final Client client;
    private final ScriptService scriptService;
    private final LoadBalancerService loadBalancerService;

    @Override
    public Object manage(UrlMappingReadDto urlMapping, HttpServletRequest request, HttpServletResponse response) throws TargetMethodManagerException {

        String message;
        int scInternalServerError = HttpServletResponse.SC_OK;
        try {

            // pobierz adres dla mappingu
            Long urlMappingId = urlMapping.getUrlMappingId();
            LoadBalancerIpOutputData hostIp = loadBalancerService.getHostIp(urlMappingId);
            String addressIp = hostIp.getAddressIp();

            // wykonaj skrypty na danych z body
            String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            body = scriptService.executeScripts(urlMappingId, body);

            // wykonaj zapytanie do serwera targetowego
            TargetEndpointDto target = urlMapping.getTarget();
            String fullUrl = target.getFullUrl(addressIp);

            WebTarget target1 = client.target(fullUrl);
            for (Map.Entry<String, String[]> item : request.getParameterMap().entrySet()) {
                target1 = target1.queryParam(item.getKey(), item.getValue()[0]);
            }

            // 2. przepisz dane z naglowka
            MultivaluedMap<String, Object> headers = new MultivaluedHashMap<>();
            for (Map.Entry<String, String> item : HttpServletRequestUtil.getHeaderAsMap(request).entrySet()) {
                headers.add(item.getKey(), item.getValue());
            }

            javax.ws.rs.core.Response post = target1
                    .request()
                    .post(Entity.entity(body, MediaType.APPLICATION_FORM_URLENCODED_TYPE));

            message = post.readEntity(String.class);
        } catch (Exception e) {
            e.printStackTrace();
            scInternalServerError = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
            NetIntegratorAppResponse src = new NetIntegratorAppResponse();
            src.setMessage(e.getMessage());
            message = gson.toJson(src);
        }


        // przygotuj odpowiedz
        try {
            response.setStatus(scInternalServerError);
            PrintWriter writer = response.getWriter();
            writer.write(message);
            writer.flush();
            writer.close();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return response;
    }
}

