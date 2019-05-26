package pl.inzynier.netintegrator.server.module.urlmapping;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Arrays;


@Value
@RestController
class UrlMappingController {

    UrlMappingRepository urlMappingRepository;

    @Autowired
    public UrlMappingController(UrlMappingRepository urlMappingRepository) {
        this.urlMappingRepository = urlMappingRepository;
    }

    @GetMapping("/test")
    ResponseEntity<String> test() {
        return new ResponseEntity<>("Eloszka bitch", HttpStatus.OK);
    }


}
