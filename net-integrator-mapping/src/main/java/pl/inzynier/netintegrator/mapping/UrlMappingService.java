package pl.inzynier.netintegrator.mapping;

import pl.inzynier.netintegrator.http.util.RequestMethod;
import pl.inzynier.netintegrator.mapping.dto.UrlMappingReadDto;
import pl.inzynier.netintegrator.mapping.dto.UrlMappingServiceException;
import pl.inzynier.netintegrator.mapping.dto.UrlMappingWriteDto;

import java.util.List;
import java.util.Optional;


public interface UrlMappingService {

    Long addUrlMapping(UrlMappingWriteDto mappingDto) throws UrlMappingServiceException;

    Optional<UrlMappingReadDto> findByPublishUrlAndPublishMethod(String url, RequestMethod mapping) throws UrlMappingServiceException;

    void deactivateUrlMapping(Long urlMappingId) throws UrlMappingServiceException;

    List<UrlMappingReadDto> findAll() throws UrlMappingServiceException;


}
