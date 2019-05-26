package pl.inzynier.netintegrator.fakeserver;

import com.google.gson.Gson;
import lombok.Builder;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;
import spark.Service;

@Builder
class FakeServerController {

    Gson gson;

    void initMapping(Service service) {
        service.get("/test", this::test, gson::toJson);
    }

    Object test(Request request, Response response) {

        FakeServerReponse reponse = FakeServerReponse.builder()
                .message("Hello from FakeServer")
                .object(request.queryMap().toMap())
                .statusCode(HttpStatus.OK_200)
                .build();

        return reponse;
    };

}
