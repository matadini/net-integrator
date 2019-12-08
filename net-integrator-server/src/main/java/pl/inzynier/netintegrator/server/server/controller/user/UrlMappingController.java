package pl.inzynier.netintegrator.server.server.controller.user;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.http.spark.SparkController;
import pl.inzynier.netintegrator.user.core.UserService;
import pl.inzynier.netintegrator.user.core.dto.UserWriteDTO;
import spark.Request;
import spark.Response;
import spark.Service;

@RequiredArgsConstructor
public class UrlMappingController implements SparkController {

    private final Gson gson;
    private final UserService userService;

    @Override
    public void initialize(Service service) {

    }

    private Object addUser(Request request, Response response) {
        return null;
    }

    private Object authorization(Request request, Response response) {
        try {
            String body = request.body();
            UserWriteDTO userWriteDTO = gson.fromJson(body, UserWriteDTO.class);
            System.out.println(userWriteDTO);
        } catch (Exception ex) {

        }
return  null;
    }

    private Object removeUser(Request request, Response response) {
return null;
    }

}
