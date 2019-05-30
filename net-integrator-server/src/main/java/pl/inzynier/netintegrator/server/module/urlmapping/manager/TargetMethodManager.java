package pl.inzynier.netintegrator.server.module.urlmapping.manager;

import org.springframework.http.ResponseEntity;
import pl.inzynier.netintegrator.server.module.urlmapping.dto.UrlMappingDto;

import javax.servlet.http.HttpServletRequest;

public interface TargetMethodManager {
    ResponseEntity<String> manage(UrlMappingDto urlMapping, HttpServletRequest request);
}



