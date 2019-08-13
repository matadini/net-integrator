package pl.inzynier.netintegrator.server.module.core;

import com.google.gson.Gson;
import lombok.Value;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Targetowy kontroler na ktorym wystawiane sa mapowania z konfiguracji/bazy
 */
@Value
@RestController
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
