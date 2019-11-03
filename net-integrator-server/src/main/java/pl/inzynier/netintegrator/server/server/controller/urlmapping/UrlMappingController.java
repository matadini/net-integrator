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
        service.delete("/admin/url-mapping/delete/:id", this::delete);

    }

    private Object delete(Request request, Response response) {
        try {
            String id = request.params("id");
            Long aLong = Long.valueOf(id);
            urlMappingService.delete(aLong);

            response.status(HttpStatus.OK_200);
            return response;

        } catch (Exception ex) {
            return "error";
        }

    }


    private Object create(Request request, Response response) {

        try {

            // zapisz nowy mapping do bazy
            String body = request.body();
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

    private Object update(Request request, Response response) {
        try {
            String body = request.body();
            UrlMappingReadDto urlMappingWriteDto = gson.fromJson(body, UrlMappingReadDto.class);
            urlMappingService.update(urlMappingWriteDto);
            response.status(HttpStatus.OK_200);
        } catch (Exception e) {
            return "Error";
        }
        return response;
    }

    private Object findAll(Request request, Response response) {

        try {
            List<UrlMappingReadDto> all = urlMappingService.findAll();
            return gson.toJson(all);

        } catch (UrlMappingServiceException e) {
            return "Error";
        }

    }
}

