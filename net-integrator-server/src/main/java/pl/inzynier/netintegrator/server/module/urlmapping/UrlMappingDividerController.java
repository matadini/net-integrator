package pl.inzynier.netintegrator.server.module.urlmapping;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController(UrlMappingConst.DIVIDER_CONTROLLER_NAME)
class UrlMappingDividerController {

    public ResponseEntity<Object> handler(HttpServletRequest request,
                                   HttpServletResponse response) {

        System.out.println(request.toString() + "\n" + response.toString());
        return ResponseEntity.ok("elo");
    }


}
