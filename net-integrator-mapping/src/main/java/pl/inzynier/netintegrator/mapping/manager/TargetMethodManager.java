package pl.inzynier.netintegrator.mapping.manager;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;

import pl.inzynier.netintegrator.mapping.dto.UrlMappingDto;

public interface TargetMethodManager {
    ResponseEntity<String> manage(UrlMappingDto urlMapping, HttpServletRequest request) throws TargetMethodManagerException;
}



