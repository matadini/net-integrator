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
        service.get("/fake-get", this::getTest, gson::toJson);
        service.post("/fake-post", this::postTest, gson::toJson);
        service.get("/test", this::test, gson::toJson);
    }

    private Object getTest(Request request, Response response) {

        return FakeServerReponse.builder()
                .message("Get response from FakeServer")
                .object(request.queryMap().toMap())
                .statusCode(HttpStatus.OK_200)
                .build();
    }

    private Object postTest(Request request, Response response) {

        return FakeServerReponse.builder()
                .message("Post response from FakeServer")
                .object(request.body())
                .statusCode(HttpStatus.OK_200)
                .build();

    }

    private Object test(Request request, Response response) {
        return "Fake server dziala! ;)";
    }


}
