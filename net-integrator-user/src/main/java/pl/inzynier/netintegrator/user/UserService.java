package pl.inzynier.netintegrator.user;

import pl.inzynier.netintegrator.user.dto.UserWriteDTO;

public interface UserService {

    Long addUser(UserWriteDTO dto) throws UserServiceException;

    boolean authorization(UserWriteDTO dto) throws UserServiceException;

    void removeUser(Long userId) throws UserServiceException;
}

