package pl.inzynier.netintegrator.client.mapping;

import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingClientException;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingReadDto;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingWriteDto;

import java.util.List;


public interface UrlMappingClient {

    Long addUrlMapping(UrlMappingWriteDto mappingDto) throws UrlMappingClientException;

    void deactivateUrlMapping(Long urlMappingId) throws UrlMappingClientException;

    List<UrlMappingReadDto> findAll() throws UrlMappingClientException;


    static UrlMappingClient create(String address) {
        return new UrlMappingClientStub();
    }
}

