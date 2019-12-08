package pl.inzynier.netintegrator.server.server.controller.script;

import com.google.gson.Gson;
import org.eclipse.jetty.http.HttpStatus;
import pl.inzynier.netintegrator.script.core.ScriptService;
import pl.inzynier.netintegrator.script.core.dto.ScriptReadDto;
import pl.inzynier.netintegrator.script.core.dto.ScriptWriteDto;
import pl.inzynier.netintegrator.server.server.controller.BaseController;
import spark.Request;
import spark.Response;
import spark.Service;

import java.util.List;


public class ScriptController extends BaseController {

    private final ScriptService scriptService;

    public ScriptController(Gson gson, ScriptService scriptService) {
        super(gson);
        this.scriptService = scriptService;
    }

    @Override
    public void initialize(Service service) {
        service.post("/admin/script/create", this::create);
        service.delete("/admin/script/delete/:id", this::delete);
        service.get("/admin/script/find-by-urlmapping-id/:id", this::findByUrlMappingId);
    }

    private Object create(Request request, Response response) {
        try {

            String body = request.body();
            ScriptWriteDto urlMappingWriteDto = gson.fromJson(body, ScriptWriteDto.class);
            return scriptService.create(urlMappingWriteDto);

        } catch (Exception ex) {
            ex.printStackTrace();
            response.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
            response.body(ex.getMessage());
            return response;
        }
    }

    private Object delete(Request request, Response response) {
        return null;
    }

    private Object findByUrlMappingId(Request request, Response response) {
        try {

            String id = request.params("id");
            Long aLong = Long.valueOf(id);
            List<ScriptReadDto> byUrlMappingId = scriptService.findByUrlMappingId(aLong);
            return gson.toJson(byUrlMappingId);

        } catch (Exception ex) {
            ex.printStackTrace();
            response.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
            response.body(ex.getMessage());
            return response;
        }
    }
}
