package pl.inzynier.netintegrator.server.module.urlmapping;

import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

interface TargetMethodManager {
    ResponseEntity<String> manage(UrlMapping urlMapping, HttpServletRequest request);
}

