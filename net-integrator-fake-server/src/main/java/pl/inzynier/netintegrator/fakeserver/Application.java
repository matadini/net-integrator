package pl.inzynier.netintegrator.fakeserver;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.GsonBuildConfig;
import lombok.Builder;
import lombok.Value;
import org.eclipse.jetty.http.HttpStatus;
import spark.Request;
import spark.Response;
import spark.Service;


public class Application {

    public static void main(String[] args) {

        Gson gson = new GsonBuilder().serializeNulls().create();

        Service service = Service.ignite().port(9090);

        FakeServerController build = FakeServerController.builder()
                .gson(gson)
                .build();
        build.initMapping(service);

    }


}
