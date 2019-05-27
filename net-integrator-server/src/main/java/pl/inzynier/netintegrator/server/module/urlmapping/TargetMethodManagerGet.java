package pl.inzynier.netintegrator.server.module.urlmapping;

import lombok.AllArgsConstructor;
import lombok.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Value
@AllArgsConstructor
class TargetMethodManagerGet implements TargetMethodManager {

    RestTemplate restTemplate;

    @Override
    public ResponseEntity<String> manage(TargetEndpoint target, HttpServletRequest request) {

        // utworz nowy URI na podstawie target + parametrow get
        String fullUrl = target.getFullUrl();
        Map<String, String[]> parameterMap1 = request.getParameterMap();

        // przepisz zapytanie na nowy URL
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(fullUrl);
        for (String key : parameterMap1.keySet()) {
            for (String value : parameterMap1.get(key)) {
                uriComponentsBuilder.queryParam(key, value);
            }
        }
        String s = uriComponentsBuilder.toUriString();

        // wyslij zadanie
        return restTemplate.getForEntity(s, String.class);
    }
}
