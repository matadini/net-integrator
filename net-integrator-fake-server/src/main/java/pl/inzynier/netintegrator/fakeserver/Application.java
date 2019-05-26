package pl.inzynier.netintegrator.fakeserver;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.internal.GsonBuildConfig;
import spark.Service;

public class Application {

    public static void main(String[] args) {

        Gson gson = new GsonBuilder().serializeNulls().create();

        Service service = Service.ignite().port(9090);
        service.get("/test", (req, resp) -> {
            return "Hello from spark fake server";
        }, gson::toJson);

    }


}
