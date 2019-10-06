package pl.inzynier.netintegrator.server.httpmethod.mapping;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import groovy.lang.GroovyShell;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.http.util.HttpServletRequestUtil;
import pl.inzynier.netintegrator.http.util.ParameterStringBuilder;
import pl.inzynier.netintegrator.http.util.RequestMethod;
import pl.inzynier.netintegrator.loadbalancer.LoadBalancerService;
import pl.inzynier.netintegrator.loadbalancer.dto.LoadBalancerIpOutputData;
import pl.inzynier.netintegrator.mapping.dto.TargetEndpointDto;
import pl.inzynier.netintegrator.mapping.dto.UrlMappingReadDto;
import pl.inzynier.netintegrator.script.ScriptService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

class NetIntegratorAppResponse {

}

@RequiredArgsConstructor
class TargetMethodManagerGet implements TargetMethodManager {

    private final Gson gson;
    private final GroovyShell groovyShell;
    private final ScriptService scriptService;
    private final LoadBalancerService loadBalancerService;

    @Override
    public Object manage(UrlMappingReadDto urlMapping, HttpServletRequest request, HttpServletResponse response) throws TargetMethodManagerException {

        String message = "";
        int scInternalServerError = HttpServletResponse.SC_OK;

        try {

            // pobierz adres dla mappingu
            Long urlMappingId = urlMapping.getUrlMappingId();
            LoadBalancerIpOutputData hostIp = loadBalancerService.getHostIp(urlMappingId);
            String addressIp = hostIp.getAddressIp();

            // wykonaj skrypty na danych z body
            String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            if(Objects.nonNull(body)) {
                String bodyAfterScript = scriptService.executeScripts(urlMappingId, body);
            }
            // wykonaj zapytanie do serwera targetowego
            TargetEndpointDto target = urlMapping.getTarget();
            String fullUrl = target.getFullUrl(addressIp);

            URL url = new URL(fullUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(RequestMethod.GET.toString());

            // 1. przepisz parametry URL
            Map<String, String> parameters = Maps.newHashMap();
            Map<String, String[]> parameterMap = request.getParameterMap();
            for (Map.Entry<String, String[]> item : parameterMap.entrySet()) {
                parameters.put(item.getKey(), item.getValue()[0]);
            }

            connection.setDoOutput(true);
            DataOutputStream out = new DataOutputStream(connection.getOutputStream());
            out.writeBytes(ParameterStringBuilder.getParamsString(parameters));
            out.flush();
            out.close();

            // 2. przepisz dane z naglowka
            Map<String, String> headerAsMap = HttpServletRequestUtil.getHeaderAsMap(request);
            for (Map.Entry<String, String> item : headerAsMap.entrySet()) {
                connection.setRequestProperty(item.getKey(), item.getValue()); //"Content-Type", "application/json");
            }

            // 3. odczytaj odpowiedz
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();

            // 4. zamknij polaczenie
            connection.disconnect();

        } catch (Exception e) {
            scInternalServerError = HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
            message = e.getMessage();
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
