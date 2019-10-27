package pl.inzynier.netintegrator.server.server;

import lombok.RequiredArgsConstructor;
import org.pmw.tinylog.Logger;
import pl.inzynier.netintegrator.http.util.RequestMethod;
import pl.inzynier.netintegrator.mapping.UrlMappingService;
import pl.inzynier.netintegrator.mapping.dto.PublishEndpointDto;
import pl.inzynier.netintegrator.mapping.dto.UrlMappingReadDto;
import pl.inzynier.netintegrator.script.ScriptService;
import spark.Service;

import java.util.List;

@RequiredArgsConstructor
public class NetIntegratorServer {

    private Service service;
    //private final ScriptService scriptService;
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
                addRouting(mapping);
            }

            service.get("/test", (req, resp) -> "Hello test from net-integrator-server!");
        } catch (Exception ex) {
            Logger.info(ex);
        }
    }

    public void addRouting(UrlMappingReadDto mapping) {
        PublishEndpointDto endpoint = mapping.getEndpoint();
        if (RequestMethod.GET.equals(endpoint.getMethod())) {
            String methodUrl = endpoint.getMethodUrl();
            service.get(methodUrl, route);
        }
        if (RequestMethod.POST.equals(endpoint.getMethod())) {
            String methodUrl = endpoint.getMethodUrl();
            service.post(methodUrl, route);
        }
    }
}
