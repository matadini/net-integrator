package pl.inzynier.netintegrator.server.httpmethod.mapping;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.inzynier.netintegrator.script.core.ScriptService;
import pl.inzynier.netintegrator.server.httpmethod.generator.HttpMethodMapKeys;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TargetMethodManagerFactory {
    
    public static Map<String, TargetMethodManager> create(ScriptService scriptService) {

        Gson gson = new GsonBuilder().serializeNulls().create();

        Map<String, TargetMethodManager> requestMethodManagerStrategyMap = Maps.newHashMap();
        requestMethodManagerStrategyMap.put(HttpMethodMapKeys.GET_TO_GET,  new TargetMethodManagerGetToGet(gson, scriptService));
        requestMethodManagerStrategyMap.put(HttpMethodMapKeys.POST_TO_POST, new TargetMethodManagerPostToPost(gson, scriptService));

        return requestMethodManagerStrategyMap;
    }
}
