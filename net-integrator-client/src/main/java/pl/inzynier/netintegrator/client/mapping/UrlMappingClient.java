package pl.inzynier.netintegrator.client.mapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingClientException;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingReadDto;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingWriteDto;

import java.util.List;


public interface UrlMappingClient {

    Long create(UrlMappingWriteDto dto) throws UrlMappingClientException;

    void update(UrlMappingReadDto dto) throws UrlMappingClientException;

    List<UrlMappingReadDto> findAll() throws UrlMappingClientException;

    void deactivate(Long urlMappingId) throws UrlMappingClientException;


    static UrlMappingClient create() {
        return new UrlMappingClientStub();
    }

    static UrlMappingClient create(String address) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return new UrlMappingClientHttp(address, gson);
    }
}

