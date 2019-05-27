package pl.inzynier.netintegrator.server.module.urlmapping;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

interface UrlMappingDividerService {
    ResponseEntity<String> manage(HttpServletRequest request, HttpServletResponse response) throws UrlMappingDividerServiceException;
}
