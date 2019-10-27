package pl.inzynier.netintegrator.client.mapping;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingClientException;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingReadDto;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingWriteDto;

import javax.ws.rs.client.Client;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RequiredArgsConstructor
class UrlMappingClientHttp implements UrlMappingClient {

    private final String address;
    private final Client client;
    private final Gson gson;

    @Override
    public Long create(UrlMappingWriteDto dto) throws UrlMappingClientException {
        return null;
    }

    @Override
    public void update(UrlMappingReadDto dto) throws UrlMappingClientException {

    }

    @Override
    public List<UrlMappingReadDto> findAll() throws UrlMappingClientException {
        String target = address + "/admin/url-mapping/find-all";

        String s = client.target(target).request().get(String.class);
        return gson.fromJson(s, new TypeToken<List<UrlMappingReadDto>>() {
        }.getType());
        // return Lists.newArrayList();

    }

    @Override
    public void deactivate(Long urlMappingId) throws UrlMappingClientException {

    }
}
