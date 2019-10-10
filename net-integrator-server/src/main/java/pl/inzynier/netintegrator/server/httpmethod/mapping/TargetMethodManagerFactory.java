package pl.inzynier.netintegrator.server.httpmethod.mapping;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.inzynier.netintegrator.loadbalancer.LoadBalancerService;
import pl.inzynier.netintegrator.script.ScriptService;
import pl.inzynier.netintegrator.server.httpmethod.generator.HttpMethodMapKeys;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TargetMethodManagerFactory {
    
    public static Map<String, TargetMethodManager> create(LoadBalancerService loadBalancerService, ScriptService scriptService) {

        Gson gson = new GsonBuilder().serializeNulls().create();
        Client client = ClientBuilder.newClient();

        Map<String, TargetMethodManager> requestMethodManagerStrategyMap = Maps.newHashMap();
        requestMethodManagerStrategyMap.put(HttpMethodMapKeys.GET_TO_GET,  new TargetMethodManagerGetToGet(gson, client, loadBalancerService));
        requestMethodManagerStrategyMap.put(HttpMethodMapKeys.POST_TO_POST, new TargetMethodManagerPostToPost(gson, client, scriptService, loadBalancerService));

        return requestMethodManagerStrategyMap;
    }
}
