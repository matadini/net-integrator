package pl.inzynier.netintegrator.server.httpmethod.mapping;

import javax.servlet.http.HttpServletRequest;


import pl.inzynier.netintegrator.mapping.dto.UrlMappingWriteDto;

/**
 * Interfejs dla strategii mapowania requestow
 * - GET TO POST
 * - GET TO GET itp.
 */
public interface TargetMethodManager {
    String manage(UrlMappingWriteDto urlMapping, HttpServletRequest request) throws TargetMethodManagerException;
}



