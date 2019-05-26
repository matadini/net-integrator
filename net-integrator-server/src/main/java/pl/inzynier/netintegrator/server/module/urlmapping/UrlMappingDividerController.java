package pl.inzynier.netintegrator.server.module.urlmapping;

import com.google.gson.Gson;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

/**
 * Targetowy kontroler na ktorym wystawiane sa mapowania z konfiguracji/bazy
 */
@Value
@RestController(UrlMappingConst.DIVIDER_CONTROLLER_NAME)
class UrlMappingDividerController {

    Gson gson;
    RestTemplate restTemplate;
    UrlMappingRepository mappingRepository;

    @Autowired
    public UrlMappingDividerController(Gson gson, UrlMappingRepository mappingRepository, RestTemplate restTemplate) {
        this.gson = gson;
        this.mappingRepository = mappingRepository;
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<String> handler(HttpServletRequest request,
                                          HttpServletResponse response) {


        RequestMethod requestMethod = RequestMethod.valueOf(request.getMethod());
        String requestURI = request.getRequestURI();

        Optional<UrlMapping> byPublishUrlAndPublishMethod = mappingRepository.findByPublishUrlAndPublishMethod(
                requestURI, requestMethod);
        if(byPublishUrlAndPublishMethod.isPresent()) {


            // todo: na jakas mape + strategie
            UrlMapping urlMapping = byPublishUrlAndPublishMethod.get();
            TargetEndpoint target = urlMapping.getTarget();
            RequestMethod method = target.getMethod();
            if(RequestMethod.GET.equals(method)) {

                String fullUrl = target.getFullUrl();
                System.out.println(fullUrl);
                Map<String, String[]> parameterMap1 = request.getParameterMap();
                ResponseEntity<String> forEntity = restTemplate.getForEntity(fullUrl, String.class, parameterMap1);
                return new ResponseEntity<>(forEntity.getBody(), HttpStatus.OK);

            }

            UrlMappingResponse build = UrlMappingResponse.builder()
                    .message("Nieobslugiwany typ metody")
                    .build();
            String toJson = gson.toJson(build);
            return new ResponseEntity<>(toJson, HttpStatus.NO_CONTENT);

        } else {

            // i tak idzie w eter
            UrlMappingResponse build = UrlMappingResponse.builder()
                    .message("Brak mapowania w bazie")
                    .build();
            String toJson = gson.toJson(build);
            return new ResponseEntity<>(toJson, HttpStatus.NO_CONTENT);

        }
    }


}
