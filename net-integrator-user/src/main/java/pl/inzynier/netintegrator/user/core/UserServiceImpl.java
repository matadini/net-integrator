package pl.inzynier.netintegrator.user.core;

import pl.inzynier.netintegrator.user.core.dto.UserWriteDTO;

class UserServiceImpl implements UserService {

    @Override
    public Long addUser(UserWriteDTO dto) throws UserServiceException {
        return null;
    }

    @Override
    public boolean authorization(UserWriteDTO dto) throws UserServiceException {
        return false;
    }

    @Override
    public void removeUser(Long userId) throws UserServiceException {

    }
}
