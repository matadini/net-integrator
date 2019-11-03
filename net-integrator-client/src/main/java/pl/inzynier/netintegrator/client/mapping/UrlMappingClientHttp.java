package pl.inzynier.netintegrator.client.mapping;

import com.google.common.collect.Lists;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingClientException;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingReadDto;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingWriteDto;

import java.util.List;

@RequiredArgsConstructor
class UrlMappingClientHttp implements UrlMappingClient {

    private final String address;
    private final Gson gson;

    @Override
    public Long create(UrlMappingWriteDto dto) throws UrlMappingClientException {
        String target = address + "/admin/url-mapping/create";
        String jsonToSend = gson.toJson(dto);

        HttpResponse<String> stringHttpResponse = Unirest.post(target).body(jsonToSend).asString();
        System.out.println(stringHttpResponse.getBody());
        return Long.valueOf(stringHttpResponse.getBody());
    }

    @Override
    public void update(UrlMappingWriteDto dto, Long urlMappingId) throws UrlMappingClientException {
        String target = address + "/admin/url-mapping/update/" + urlMappingId;
        String jsonToSend = gson.toJson(dto);
        HttpResponse httpResponse = Unirest.post(target).body(jsonToSend).asEmpty();
    }

    @Override
    public List<UrlMappingReadDto> findAll() throws UrlMappingClientException {
        String target = address + "/admin/url-mapping/find-all";

        String s = Unirest.get(target).asString().getBody();
        // String s = client.target(target).request().get(String.class);
        return gson.fromJson(s, new TypeToken<List<UrlMappingReadDto>>() {
        }.getType());

    }

    @Override
    public void delete(Long urlMappingId) throws UrlMappingClientException {


        String target = address + "/admin/url-mapping/delete/" + urlMappingId;
        HttpResponse httpResponse = Unirest.delete(target).asEmpty();
        System.out.println(httpResponse.getStatus());
    }
}
