package pl.inzynier.netintegrator.server.httpmethod.mapping;

import com.google.gson.Gson;
import kong.unirest.GetRequest;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.NonNull;
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
class TargetMethodManagerGetToGet implements TargetMethodManager {

    private final Gson gson;
    private final ScriptService scriptService;

    @Override
    public Object manage(
            UrlMappingReadDto urlMapping,
            HttpServletRequest request,
            HttpServletResponse response) {

        String message;
        int scInternalServerError = HttpServletResponse.SC_OK;

        try {
            // uzyskaj adres docelowy
            TargetEndpointDto target = urlMapping.getTarget();
            String fullUrl = target.getFullUrl();
            GetRequest getRequest = Unirest.get(fullUrl);

            // 1. przepisz query param URL do nowego zapytania
            Map<String, String[]> paramMap = request.getParameterMap();
            for (Map.Entry<String, String[]> item : paramMap.entrySet()) {
                String key = item.getKey();
                String value = item.getValue()[0];
                getRequest = getRequest.queryString(key, value);
            }

            // 2. przepisz dane z naglowka do nowego zapytania
            Map<String, String> headerMap =
                    HttpServletRequestUtil.getHeaderAsMap(request);

            for (Map.Entry<String, String> item : headerMap.entrySet()) {
                String key = item.getKey();
                String value = item.getValue();

                // podmien wartosc naglowkowa HOST
                if("Host".equals(key)) {
                    @NonNull String address = target.getHostAddress();
                    value = address.replace("http://", "");
                }
                getRequest = getRequest.header(key, value);
            }

            // wykonaj zapytanie na adres docelowy
            HttpResponse<String> httpResponse = getRequest.asString();
            message = httpResponse.getBody();

            // wykonaj skrypty POST_CALL
            Long urlMappingId = urlMapping.getUrlMappingId();
            message = scriptService.executeScripts(
                    urlMappingId, message, ScriptType.POST_CALL);

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
