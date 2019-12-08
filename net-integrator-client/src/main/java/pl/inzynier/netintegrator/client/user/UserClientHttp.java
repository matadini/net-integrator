package pl.inzynier.netintegrator.client.user;

import com.google.gson.Gson;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.user.dto.UserWriteDTO;

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
}
