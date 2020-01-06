package pl.inzynier.netintegrator.user.core;

import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.user.core.dto.UserReadDTO;
import pl.inzynier.netintegrator.user.core.dto.UserWriteDTO;
import pl.inzynier.netintegrator.user.util.PasswordUtil;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Long create(UserWriteDTO dto) throws UserServiceException {

        String login = dto.getLogin();
        if (Objects.nonNull(userRepository.findByLogin(login))) {
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
        String password = PasswordUtil.stringToSha256String(dto.getPassword());
        return Objects.nonNull(userRepository.findByLoginAndPassword(dto.getLogin(), password));
    }

    @Override
    public void delete(Long userId) throws UserServiceException {
        Optional<User> byId = userRepository.findById(userId);
        if(byId.isPresent()) {
            userRepository.delete(byId.get());
        }
    }

    @Override
    public List<UserReadDTO> findAll() throws UserServiceException {
        return userRepository.findAll().stream()
                .map(x -> new UserReadDTO(x.userId, x.login, x.password))
                .collect(Collectors.toList());
    }
}
