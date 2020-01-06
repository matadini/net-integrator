package pl.inzynier.netintegrator.user.core;

import pl.inzynier.netintegrator.user.core.dto.UserReadDTO;
import pl.inzynier.netintegrator.user.core.dto.UserWriteDTO;

import java.util.List;

public interface UserService {

    Long create(UserWriteDTO dto) throws UserServiceException;

    boolean authorization(UserWriteDTO dto) throws UserServiceException;

    void delete(Long userId) throws UserServiceException;

    List<UserReadDTO> findAll() throws UserServiceException;
}

