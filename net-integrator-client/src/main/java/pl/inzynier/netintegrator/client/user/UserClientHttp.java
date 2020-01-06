package pl.inzynier.netintegrator.client.user;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.script.dto.ScriptReadDto;
import pl.inzynier.netintegrator.client.user.dto.UserClientException;
import pl.inzynier.netintegrator.client.user.dto.UserReadDTO;
import pl.inzynier.netintegrator.client.user.dto.UserWriteDTO;

import java.util.List;

@RequiredArgsConstructor
class UserClientHttp implements UserClient {

    private final String address;
    private final Gson gson;

    @Override
    public boolean authorization(UserWriteDTO dto) {
        String target = address + "/admin/user/authorization";
        String jsonToSend = gson.toJson(dto);
        HttpResponse<String> httpResponse = Unirest.post(target).body(jsonToSend).asString();
        String s = httpResponse.getBody();
        return Boolean.valueOf(s);
    }

    @Override
    public List<UserReadDTO> getAll() throws UserClientException {
        String target = address + "/admin/user/get-all"; // + urlMappingId;
        String s = Unirest.get(target).asString().getBody();
        return gson.fromJson(s, new TypeToken<List<UserReadDTO>>() {
        }.getType());
    }

    @Override
    public Long create(UserWriteDTO dto) throws UserClientException {
        String target = address + "/admin/user/create";
        String jsonToSend = gson.toJson(dto);

        HttpResponse<String> stringHttpResponse = Unirest.post(target).body(jsonToSend).asString();
        return Long.valueOf(stringHttpResponse.getBody());
    }

    @Override
    public void delete(Long id) throws UserClientException {
        String target = address + "/admin/user/delete/" + id;
        HttpResponse httpResponse = Unirest.delete(target).asEmpty();
        System.out.println(httpResponse.getStatus());
    }
}
