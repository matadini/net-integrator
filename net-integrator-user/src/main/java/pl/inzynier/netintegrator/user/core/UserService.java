package pl.inzynier.netintegrator.user.core;

import pl.inzynier.netintegrator.user.core.dto.UserWriteDTO;

public interface UserService {

    Long addUser(UserWriteDTO dto) throws UserServiceException;

    boolean authorization(UserWriteDTO dto) throws UserServiceException;

    void removeUser(Long userId) throws UserServiceException;
}

