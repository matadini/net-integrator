package pl.inzynier.netintegrator.client.user;

import com.google.gson.Gson;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.RequiredArgsConstructor;
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
        System.out.println(s);
        return Boolean.valueOf(s);
    }

    @Override
    public List<UserReadDTO> getAll() throws UserClientException {
        return null;
    }

    @Override
    public Long add(UserWriteDTO dto) throws UserClientException {
        return null;
    }

    @Override
    public void remove(Long id) throws UserClientException {

    }
}
