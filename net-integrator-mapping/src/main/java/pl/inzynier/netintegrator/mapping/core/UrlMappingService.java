package pl.inzynier.netintegrator.mapping.core;

import pl.inzynier.netintegrator.http.util.RequestMethod;
import pl.inzynier.netintegrator.mapping.core.dto.UrlMappingReadDto;
import pl.inzynier.netintegrator.mapping.core.dto.UrlMappingServiceException;
import pl.inzynier.netintegrator.mapping.core.dto.UrlMappingWriteDto;

import java.util.List;
import java.util.Optional;


public interface UrlMappingService {

    Long create(UrlMappingWriteDto mappingDto) throws UrlMappingServiceException;

    void update(UrlMappingWriteDto mappingDto, Long urlMappingId) throws UrlMappingServiceException;

    Optional<UrlMappingReadDto> findByPublishUrlAndPublishMethod(String url, RequestMethod mapping) throws UrlMappingServiceException;

    void delete(Long urlMappingId) throws UrlMappingServiceException;

    List<UrlMappingReadDto> findAll() throws UrlMappingServiceException;

    UrlMappingReadDto findById(Long urlMappingId) throws UrlMappingServiceException;

}
