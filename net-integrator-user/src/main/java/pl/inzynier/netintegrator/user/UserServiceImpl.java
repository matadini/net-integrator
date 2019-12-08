package pl.inzynier.netintegrator.user;

import pl.inzynier.netintegrator.user.dto.UserWriteDTO;

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
