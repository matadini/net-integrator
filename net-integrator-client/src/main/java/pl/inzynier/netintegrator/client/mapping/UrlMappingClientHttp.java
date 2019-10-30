package pl.inzynier.netintegrator.client.mapping;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingClientException;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingReadDto;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingWriteDto;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@RequiredArgsConstructor
class UrlMappingClientHttp implements UrlMappingClient {

    private final String address;
    private final Client client;
    private final Gson gson;

    @Override
    public Long create(UrlMappingWriteDto dto) throws UrlMappingClientException {
        String target = address + "/admin/url-mapping/create";
        String jsonToSend = gson.toJson(dto);
        Entity<String> entity = Entity.entity(jsonToSend, MediaType.TEXT_PLAIN);
        String post = client.target(target).request().post(entity, String.class);
        System.out.println(post);
        return 0L;
    }

    @Override
    public void update(UrlMappingReadDto dto) throws UrlMappingClientException {
        String target = address + "/admin/url-mapping/update";
        String jsonToSend = gson.toJson(dto);
        client.target(target).request().post(Entity.entity(jsonToSend, MediaType.TEXT_PLAIN));
    }

    @Override
    public List<UrlMappingReadDto> findAll() throws UrlMappingClientException {
        String target = address + "/admin/url-mapping/find-all";

        String s = client.target(target).request().get(String.class);
        return gson.fromJson(s, new TypeToken<List<UrlMappingReadDto>>() {
        }.getType());

    }

    @Override
    public void deactivate(Long urlMappingId) throws UrlMappingClientException {

    }
}
