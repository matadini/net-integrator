package pl.inzynier.netintegrator.server.server.controller.user;

import com.google.gson.Gson;
import org.eclipse.jetty.http.HttpStatus;
import pl.inzynier.netintegrator.server.server.controller.BaseController;
import pl.inzynier.netintegrator.user.core.UserService;
import pl.inzynier.netintegrator.user.core.dto.UserWriteDTO;
import spark.Request;
import spark.Response;
import spark.Service;

import java.util.ArrayList;


public class UserController extends BaseController {

    private final UserService userService;

    public UserController(Gson gson, UserService userService) {
        super(gson);
        this.userService = userService;
    }


    @Override
    public void initialize(Service service) {
        service.post("/admin/user/authorization", this::authorization);
        service.post("/admin/user/create", this::addUser);
        service.get("/admin/user/get-all", this::getAll, gson::toJson);
        service.delete("/admin/user/delete/:id", this::delete);
    }

    private Object addUser(Request request, Response response) {
        try {

            String body = request.body();
            Class<UserWriteDTO> classArg = UserWriteDTO.class;
            UserWriteDTO dto = gson.fromJson(body, classArg);
            return userService.create(dto);

        } catch (Exception ex) {
            ex.printStackTrace();
            response.status(HttpStatus.INTERNAL_SERVER_ERROR_500);
            response.body(ex.getMessage());
            return response;
        }
    }

    private Object getAll(Request request, Response response) {
        try {
            return userService.findAll();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new ArrayList<>();
    }

    private Object authorization(Request request, Response response) {
        try {
            String body = request.body();
            UserWriteDTO userWriteDTO = gson.fromJson(body, UserWriteDTO.class);
            return userService.authorization(userWriteDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private Object delete(Request request, Response response) {
        try {
            String id = request.params("id");
            Long aLong = Long.valueOf(id);
            userService.delete(aLong);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

}
