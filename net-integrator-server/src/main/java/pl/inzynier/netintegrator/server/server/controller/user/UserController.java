package pl.inzynier.netintegrator.server.server.controller.user;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.http.spark.SparkController;
import pl.inzynier.netintegrator.server.server.controller.BaseController;
import pl.inzynier.netintegrator.user.core.UserService;
import pl.inzynier.netintegrator.user.core.dto.UserWriteDTO;
import spark.Request;
import spark.Response;
import spark.Service;


public class UserController extends BaseController {

    private final UserService userService;

    public UserController(Gson gson, UserService userService) {
        super(gson);
        this.userService = userService;
    }


    @Override
    public void initialize(Service service) {
        service.post("/admin/user/authorization", this::authorization);
    }

    private Object addUser(Request request, Response response) {
        return null;
    }

    private Object authorization(Request request, Response response) {
        try {
            String body = request.body();
            UserWriteDTO userWriteDTO = gson.fromJson(body, UserWriteDTO.class);
            ;
            return userService.authorization(userWriteDTO);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    private Object removeUser(Request request, Response response) {
        return null;
    }

}
