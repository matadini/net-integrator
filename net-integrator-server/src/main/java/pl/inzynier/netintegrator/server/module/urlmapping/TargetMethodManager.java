package pl.inzynier.netintegrator.server.module.urlmapping;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

interface TargetMethodManager {
    ResponseEntity<String> manage(UrlMapping urlMapping, HttpServletRequest request);
}

