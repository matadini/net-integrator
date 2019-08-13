package pl.inzynier.netintegrator.server.module.urlmapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Value;


@Value
@RestController
class UrlMappingTestController {

    UrlMappingRepository urlMappingRepository;

    @Autowired
    public UrlMappingTestController(UrlMappingRepository urlMappingRepository) {
        this.urlMappingRepository = urlMappingRepository;
    }

    @GetMapping("/test")
    ResponseEntity<String> test() {
        return new ResponseEntity<>("Eloszka bitch", HttpStatus.OK);
    }


}
