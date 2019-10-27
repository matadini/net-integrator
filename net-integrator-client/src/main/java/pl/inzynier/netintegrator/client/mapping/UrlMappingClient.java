package pl.inzynier.netintegrator.client.mapping;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingClientException;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingReadDto;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingWriteDto;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
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
        Client client = ClientBuilder.newClient();
        Gson gson = new GsonBuilder().serializeNulls().create();
        return new UrlMappingClientHttp(address, client, gson);
    }
}

