package pl.inzynier.netintegrator.server.httpmethod.mapping;

import pl.inzynier.netintegrator.mapping.core.dto.UrlMappingReadDto;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

class TargetMethodManagerPutToGet implements TargetMethodManager {

    @Override
    public Object manage(UrlMappingReadDto urlMapping, HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
