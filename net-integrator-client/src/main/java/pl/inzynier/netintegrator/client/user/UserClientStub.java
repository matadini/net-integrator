package pl.inzynier.netintegrator.client.user;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.user.dto.UserWriteDTO;

class UserClientStub implements UserClient {

    @Override
    public boolean authorization(UserWriteDTO dto) {

        return "admin".equals(dto.getLogin()) && "admin".equals(dto.getPassword());
    }
}

