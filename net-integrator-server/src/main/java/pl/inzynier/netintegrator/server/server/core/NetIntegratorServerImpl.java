package pl.inzynier.netintegrator.server.server.core;

import lombok.RequiredArgsConstructor;
import org.pmw.tinylog.Logger;
import pl.inzynier.netintegrator.http.spark.SparkController;
import pl.inzynier.netintegrator.http.util.RequestMethod;
import pl.inzynier.netintegrator.mapping.core.UrlMappingService;
import pl.inzynier.netintegrator.mapping.core.dto.PublishEndpointDto;
import pl.inzynier.netintegrator.mapping.core.dto.UrlMappingReadDto;
import spark.Service;

import java.util.List;

@RequiredArgsConstructor
public class NetIntegratorServerImpl implements NetIntegratorServer {

    private Service service;
    private final UrlMappingService urlMappingService;
    private final NetIntegratorServerConfig config;
    private final NetIntegratorServerCoreRoute route;
    private final List<SparkController> adminControllers;


    public void run() {

        try {
            // uruchom serwer http
            service = Service.ignite().port(config.port);

            // pobierz konfiguracje przekierowan z bazy danych
            List<UrlMappingReadDto> mappingList = urlMappingService.findAll();

            // wystaw adresy do nasluchu
            for (UrlMappingReadDto mapping : mappingList) {
                addRouting(mapping);
            }

            // wystaw adresy metod administracyjnych
            for (SparkController controller : adminControllers) {
                controller.initialize(service);
            }
        } catch (Exception ex) {
            Logger.info(ex);
        }

    }

    @Override
    public void addRouting(UrlMappingReadDto mapping) {

        System.out.println("Publish: " + mapping);
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
