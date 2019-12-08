package pl.inzynier.netintegrator.server.httpmethod.mapping;

import pl.inzynier.netintegrator.mapping.core.dto.UrlMappingReadDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interfejs dla strategii mapowania requestow
 * - GET TO POST
 * - GET TO GET itp.
 */
@FunctionalInterface
public interface TargetMethodManager {

    Object manage(UrlMappingReadDto urlMapping, HttpServletRequest request, HttpServletResponse response);

}


