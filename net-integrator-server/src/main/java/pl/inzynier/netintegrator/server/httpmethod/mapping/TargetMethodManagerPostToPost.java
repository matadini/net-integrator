package pl.inzynier.netintegrator.server.httpmethod.mapping;

import com.google.gson.Gson;
import kong.unirest.HttpRequestWithBody;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.http.util.HttpServletRequestUtil;
import pl.inzynier.netintegrator.mapping.core.dto.TargetEndpointDto;
import pl.inzynier.netintegrator.mapping.core.dto.UrlMappingReadDto;
import pl.inzynier.netintegrator.script.core.ScriptService;
import pl.inzynier.netintegrator.script.core.dto.ScriptType;
import pl.inzynier.netintegrator.server.server.core.NetIntegratorAppResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
class TargetMethodManagerPostToPost implements TargetMethodManager {

    // private final GroovyShell groovyShell;
    private final Gson gson;
    private final ScriptService scriptService;

    @Override
    public Object manage(UrlMappingReadDto urlMapping, HttpServletRequest request, HttpServletResponse response)  {

        String message;
        int scInternalServerError = HttpServletResponse.SC_OK;
        try {


            // wykonaj skrypty na danych z body
            Long urlMappingId = urlMapping.getUrlMappingId();
            String body = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            body = scriptService.executeScripts(urlMappingId, body, ScriptType.PRE_CALL);

            // wykonaj zapytanie do serwera targetowego
            TargetEndpointDto target = urlMapping.getTarget();
            String fullUrl = target.getFullUrl();

            HttpRequestWithBody post1 = Unirest.post(fullUrl);

            // 1. przepisz query param URL
            for (Map.Entry<String, String[]> item : request.getParameterMap().entrySet()) {
                post1 = post1.queryString(item.getKey(), item.getValue()[0]);
            }

            // 2. przepisz dane z naglowka
            for (Map.Entry<String, String> item : HttpServletRequestUtil.getHeaderAsMap(request).entrySet()) {

                String key = item.getKey();
                String value = item.getValue();

                if("Host".equals(key)) {
                    value = target.getHostAddress().replace("http://", "");
                }
                post1 = post1.header(key, value);
            }

            HttpResponse<String> stringHttpResponse = post1.body(body).asString();
            message = stringHttpResponse.getBody();

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

