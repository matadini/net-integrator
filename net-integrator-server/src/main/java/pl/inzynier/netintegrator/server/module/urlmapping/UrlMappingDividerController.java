package pl.inzynier.netintegrator.server.module.urlmapping;

import com.google.gson.Gson;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.Map;
import java.util.Optional;

/**
 * Targetowy kontroler na ktorym wystawiane sa mapowania z konfiguracji/bazy
 */
@Value
@RestController(UrlMappingConst.DIVIDER_CONTROLLER_NAME)
class UrlMappingDividerController {

    Gson gson;
    UrlMappingDividerService service;

    @Autowired
    public UrlMappingDividerController(Gson gson, UrlMappingDividerService service) {
        this.gson = gson;
        this.service = service;
    }


    public ResponseEntity<String> handler(HttpServletRequest request,
                                          HttpServletResponse response) {
        try {
            return service.manage(request, response);
        } catch (UrlMappingDividerServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
