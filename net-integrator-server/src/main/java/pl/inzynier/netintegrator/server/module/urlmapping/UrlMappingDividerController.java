package pl.inzynier.netintegrator.server.module.urlmapping;

import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    UrlMappingRepository mappingRepository;

    @Autowired
    public UrlMappingDividerController(UrlMappingRepository urlMappingRepository) {
        this.mappingRepository = urlMappingRepository;
    }

    public ResponseEntity<String> handler(HttpServletRequest request,
                                          HttpServletResponse response) {

        RequestMethod requestMethod = RequestMethod.valueOf(request.getMethod());
        String requestURI = request.getRequestURI();

        Optional<UrlMapping> byPublishUrlAndPublishMethod = mappingRepository.findByPublishUrlAndPublishMethod(
                requestURI, requestMethod);
        if(byPublishUrlAndPublishMethod.isPresent()) {

        } else {

        }


        Map<String, String[]> parameterMap = request.getParameterMap();
        System.out.println(requestURI + " " + request.getMethod() + " " + parameterMap);
        return ResponseEntity.ok("elo");
    }


}
