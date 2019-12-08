package pl.inzynier.netintegrator.client.user;

import pl.inzynier.netintegrator.client.user.dto.UserWriteDTO;

public interface UserClient {

    boolean authorization(UserWriteDTO dto);

    static UserClient create(String address) {
        return new UserClientStub();
    }
}

