package pl.inzynier.netintegrator.client.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pl.inzynier.netintegrator.client.user.dto.UserWriteDTO;

public interface UserClient {

    boolean authorization(UserWriteDTO dto);

    static UserClient create(String address) {
        final Gson gson = new GsonBuilder().serializeNulls().create();
        return new UserClientHttp(address, gson);
    }
}

