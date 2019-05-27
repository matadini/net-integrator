package pl.inzynier.netintegrator.server.module.urlmapping;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.stream.Collectors;

@AllArgsConstructor
class TargetMethodManagerPost implements TargetMethodManager {

    RestTemplate restTemplate;

    @Override
    public ResponseEntity<String> manage(TargetEndpoint target, HttpServletRequest request) {

        try {
            String fullUrl = target.getFullUrl();
            String collect = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
            return restTemplate.postForEntity(fullUrl, collect, String.class);

        } catch (IOException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
