package pl.inzynier.netintegrator.client.user;

import pl.inzynier.netintegrator.client.user.dto.UserClientException;
import pl.inzynier.netintegrator.client.user.dto.UserReadDTO;
import pl.inzynier.netintegrator.client.user.dto.UserWriteDTO;

import java.util.List;

class UserClientStub implements UserClient {

    @Override
    public boolean authorization(UserWriteDTO dto) {

        return "admin".equals(dto.getLogin()) && "admin".equals(dto.getPassword());
    }

    @Override
    public List<UserReadDTO> getAll() throws UserClientException {
        return null;
    }

    @Override
    public Long create(UserWriteDTO dto) throws UserClientException {
        return null;
    }

    @Override
    public void delete(Long id) throws UserClientException {

    }
}

