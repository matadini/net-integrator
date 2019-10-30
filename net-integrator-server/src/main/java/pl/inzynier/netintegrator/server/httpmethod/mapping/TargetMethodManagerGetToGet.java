package pl.inzynier.netintegrator.server.httpmethod.mapping;

import com.google.gson.Gson;
import kong.unirest.GetRequest;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.http.util.HttpServletRequestUtil;
import pl.inzynier.netintegrator.mapping.dto.TargetEndpointDto;
import pl.inzynier.netintegrator.mapping.dto.UrlMappingReadDto;
import pl.inzynier.netintegrator.server.server.core.NetIntegratorAppResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@RequiredArgsConstructor
class TargetMethodManagerGetToGet implements TargetMethodManager {

    private final Gson gson;
    private final Client client;


    @Override
    public Object manage(UrlMappingReadDto urlMapping, HttpServletRequest request, HttpServletResponse response) {

        String message;
        int scInternalServerError = HttpServletResponse.SC_OK;

        try {

            // uzyskaj adres targetu
            TargetEndpointDto target = urlMapping.getTarget();
            String fullUrl = target.getFullUrl();
            GetRequest getRequest = Unirest.get(fullUrl);

            // 1. przepisz query param URL
            for (Map.Entry<String, String[]> item : request.getParameterMap().entrySet()) {
                getRequest = getRequest.queryString(item.getKey(), item.getValue()[0]);
            }

            // 2. przepisz dane z naglowka
            for (Map.Entry<String, String> item : HttpServletRequestUtil.getHeaderAsMap(request).entrySet()) {

                String key = item.getKey();
                String value = item.getValue();

                if("Host".equals(key)) {
                    value = target.getHostAddress().replace("http://", "");
                }
                getRequest = getRequest.header(key, value);
            }


            HttpResponse<String> stringHttpResponse = getRequest.asString();
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
