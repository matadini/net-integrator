package pl.inzynier.netintegrator.server.server.controller.urlmapping;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.http.spark.SparkController;
import pl.inzynier.netintegrator.mapping.UrlMappingService;
import pl.inzynier.netintegrator.mapping.dto.UrlMappingReadDto;
import pl.inzynier.netintegrator.mapping.dto.UrlMappingServiceException;
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

    }


    private Object create(Request var1, Response var2) {
        return null;
    }

    Object update(Request var1, Response var2) {
        return null;
    }

    Object findAll(Request var1, Response var2) {


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

