package pl.inzynier.netintegrator.server.shared;

import com.google.common.collect.Maps;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpServletRequestUtil {

    public static Map<String, String> getHeaderAsMap(HttpServletRequest request) {
        Map<String, String> map = Maps.newHashMap();

        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }
}