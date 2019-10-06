package pl.inzynier.netintegrator.server.server;

import lombok.RequiredArgsConstructor;
import org.pmw.tinylog.Logger;
import pl.inzynier.netintegrator.http.util.RequestMethod;
import pl.inzynier.netintegrator.mapping.UrlMappingService;
import pl.inzynier.netintegrator.mapping.dto.PublishEndpointDto;
import pl.inzynier.netintegrator.mapping.dto.UrlMappingReadDto;
import pl.inzynier.netintegrator.script.ScriptService;
import pl.inzynier.netintegrator.server.httpmethod.mapping.TargetMethodManager;
import pl.inzynier.netintegrator.server.httpmethod.generator.HttpMethodMapKeyGenerator;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class NetIntegratorServer {

    private Service service;
    private final ScriptService scriptService;
    private final UrlMappingService urlMappingService;
    private final NetIntegratorServerConfig config;
    private final NetIntegratorServerCoreRoute route;

    public void run() {

        try {
            // odpal sparka
            service = Service.ignite().port(config.port);


            // pobierz mappingi i zrob publish
            List<UrlMappingReadDto> mappingList = urlMappingService.findAll();
            for (UrlMappingReadDto mapping : mappingList) {
                PublishEndpointDto endpoint = mapping.getEndpoint();
                Logger.info(endpoint);

                if (RequestMethod.GET.equals(endpoint.getMethod())) {
                    String methodUrl = endpoint.getMethodUrl();
                    service.get(methodUrl, route);
                }
                if (RequestMethod.POST.equals(endpoint.getMethod())) {
                    String methodUrl = endpoint.getMethodUrl();
                    service.post(methodUrl, route);
                }
            }

//            service.get("/test", (req, resp) -> {
//                return "Hello test!";
//            });
        } catch (Exception ex) {
            Logger.info(ex);
        }
    }
}
