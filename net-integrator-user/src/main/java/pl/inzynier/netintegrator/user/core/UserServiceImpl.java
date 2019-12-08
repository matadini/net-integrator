package pl.inzynier.netintegrator.user.core;

import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.user.core.dto.UserWriteDTO;
import pl.inzynier.netintegrator.user.util.PasswordUtil;

import java.util.Objects;

@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Long addUser(UserWriteDTO dto) throws UserServiceException {

        String login = dto.getLogin();
        if(Objects.nonNull(userRepository.findByLogin(login))) {
            throw new UserServiceException("Użytkownik o podanym loginie już istnieje");
        }

        String password1 = dto.getPassword();
        String password = PasswordUtil.stringToSha256String(password1);
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user = userRepository.save(user);
        return user.getUserId();
    }

    @Override
    public boolean authorization(UserWriteDTO dto) throws UserServiceException {
        return Objects.nonNull(userRepository.findByLoginAndPassword(dto.getLogin(), dto.getPassword()));
    }

    @Override
    public void removeUser(Long userId) throws UserServiceException {

    }
}
