package pl.inzynier.netintegrator.client.script;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingReadDto;
import pl.inzynier.netintegrator.client.script.dto.ScriptClientException;
import pl.inzynier.netintegrator.client.script.dto.ScriptReadDto;
import pl.inzynier.netintegrator.client.script.dto.ScriptWriteDto;

import java.util.List;

@RequiredArgsConstructor
class ScriptClientHttp implements ScriptClient {

    private final String address;
    private final Gson gson;

    @Override
    public Long create(ScriptWriteDto dto) throws ScriptClientException {
        String target = address + "/admin/script/create";
        String jsonToSend = gson.toJson(dto);

        HttpResponse<String> stringHttpResponse = Unirest.post(target).body(jsonToSend).asString();
        System.out.println(stringHttpResponse.getBody());
        return Long.valueOf(stringHttpResponse.getBody());
    }

    @Override
    public void update(ScriptReadDto dto) throws ScriptClientException {

    }

    @Override
    public List<ScriptReadDto> findByUrlMappingId(Long urlMappingId) throws ScriptClientException {
        String target = address + "/admin/script/find-by-urlmapping-id/" + urlMappingId;
        String s = Unirest.get(target).asString().getBody();
        return gson.fromJson(s, new TypeToken<List<ScriptReadDto>>() {
        }.getType());
    }

    @Override
    public void delete(ScriptReadDto dto) throws ScriptClientException {

    }
}
