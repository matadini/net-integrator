package pl.inzynier.netintegrator.client.user;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import pl.inzynier.netintegrator.client.user.dto.UserClientException;
import pl.inzynier.netintegrator.client.user.dto.UserReadDTO;
import pl.inzynier.netintegrator.client.user.dto.UserWriteDTO;

import java.util.List;


public interface UserClient {

    boolean authorization(UserWriteDTO dto) throws UserClientException;

    List<UserReadDTO> getAll() throws UserClientException;

    Long create(UserWriteDTO dto) throws UserClientException;

    void delete(Long id) throws UserClientException;

    static UserClient create(String address) {
        final Gson gson = new GsonBuilder().serializeNulls().create();
        return new UserClientHttp(address, gson);
    }
}

