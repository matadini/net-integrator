package pl.inzynier.netintegrator.server.server.controller.urlmapping;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.eclipse.jetty.http.HttpStatus;
import pl.inzynier.netintegrator.http.spark.SparkController;
import pl.inzynier.netintegrator.mapping.UrlMappingService;
import pl.inzynier.netintegrator.mapping.dto.UrlMappingReadDto;
import pl.inzynier.netintegrator.mapping.dto.UrlMappingServiceException;
import pl.inzynier.netintegrator.mapping.dto.UrlMappingWriteDto;
import pl.inzynier.netintegrator.server.server.core.NetIntegratorContext;
import pl.inzynier.netintegrator.server.server.core.NetIntegratorServer;
import spark.Request;
import spark.Response;
import spark.Service;

import java.util.List;

@RequiredArgsConstructor
public class UrlMappingController implements SparkController {

    private final Gson gson;
    private final UrlMappingService urlMappingService;

    @Override
    public void initialize(Service service) {

        service.get("/admin/url-mapping/find-all", this::findAll);
        service.post("/admin/url-mapping/create", this::create);
        service.post("/admin/url-mapping/update", this::update);

    }


    private Object create(Request var1, Response var2) {

        try {

            // zapisz nowy mapping do bazy
            String body = var1.body();
            UrlMappingWriteDto urlMappingWriteDto = gson.fromJson(body, UrlMappingWriteDto.class);
            Long aLong = urlMappingService.create(urlMappingWriteDto);

            // pocisnij serwer aby wystawil nowy mapping
            UrlMappingReadDto byId = urlMappingService.findById(aLong);
            NetIntegratorContext context = NetIntegratorContext.getContext();
            NetIntegratorServer netIntegratorServer = context.getNetIntegratorServer();
            netIntegratorServer.addRouting(byId);

            // zwroc dane o utworzonym mappingu
            return aLong;
        } catch (Exception e) {
            return "Error";
        }
    }

    private Object update(Request var1, Response var2) {
        try {
            String body = var1.body();
            UrlMappingReadDto urlMappingWriteDto = gson.fromJson(body, UrlMappingReadDto.class);
            urlMappingService.update(urlMappingWriteDto);
            var2.status(HttpStatus.OK_200);
        } catch (Exception e) {
            return "Error";
        }
        return var2;
    }

    private Object findAll(Request var1, Response var2) {


        try {
            List<UrlMappingReadDto> all = urlMappingService.findAll();
            return gson.toJson(all);

        } catch (UrlMappingServiceException e) {


            return "Error";
        }

    }

    Object deactivate(Request var1, Response var2) {
        return null;
    }
}

