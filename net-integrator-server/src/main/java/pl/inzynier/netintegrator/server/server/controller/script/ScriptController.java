package pl.inzynier.netintegrator.server.server.controller.script;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.eclipse.jetty.http.HttpStatus;
import pl.inzynier.netintegrator.http.spark.SparkController;
import pl.inzynier.netintegrator.mapping.core.UrlMappingService;
import pl.inzynier.netintegrator.mapping.core.dto.UrlMappingWriteDto;
import pl.inzynier.netintegrator.script.core.ScriptService;
import pl.inzynier.netintegrator.server.server.controller.BaseController;
import spark.Request;
import spark.Response;
import spark.Service;


public class ScriptController extends BaseController {

    private final ScriptService scriptService;

    public ScriptController(Gson gson, ScriptService scriptService) {
        super(gson);
        this.scriptService = scriptService;
    }

    @Override
    public void initialize(Service service) {
        service.get("/admin/script/add", this::add);
        service.post("/admin/script/find-by-url-id", this::findByUrlMappingId);
    }

    private Object add(Request request, Response response) {
        try {

            String body = request.body();
            UrlMappingWriteDto urlMappingWriteDto = gson.fromJson(body, UrlMappingWriteDto.class);
            return null;

        } catch (Exception ex) {
            ex.printStackTrace();
            response.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
            response.body(ex.getMessage());
            return response;
        }
    }

    private Object findByUrlMappingId(Request request, Response response) {
        try {
            return null;
        } catch (Exception ex) {
            ex.printStackTrace();
            response.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
            response.body(ex.getMessage());
            return response;
        }
    }
}
