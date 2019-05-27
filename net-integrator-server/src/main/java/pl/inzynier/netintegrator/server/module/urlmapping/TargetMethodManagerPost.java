package pl.inzynier.netintegrator.server.module.urlmapping;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import pl.inzynier.netintegrator.server.module.script.ScriptService;
import pl.inzynier.netintegrator.server.module.script.dto.ScriptDto;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
class TargetMethodManagerPost implements TargetMethodManager {

    RestTemplate restTemplate;

    ScriptService scriptService;

    @Override
    public ResponseEntity<String> manage(UrlMapping urlMapping, HttpServletRequest request) {

        try {
            TargetEndpoint target1 = urlMapping.getTarget();
            String fullUrl = target1.getFullUrl();
            String collect = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

            List<ScriptDto> byUrlMappingId = scriptService.findByUrlMappingId(urlMapping.getUrlMappingId());
            System.out.println("Skryptow do wykonania: " + byUrlMappingId.size());

            return restTemplate.postForEntity(fullUrl, collect, String.class);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
