package pl.inzynier.netintegrator.client.user;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.user.dto.UserWriteDTO;

@RequiredArgsConstructor
class UserClientHttp implements UserClient {

    private final String address;
    private final Gson gson;

    @Override
    public boolean authorization(UserWriteDTO dto) {
        return false;
    }
}
