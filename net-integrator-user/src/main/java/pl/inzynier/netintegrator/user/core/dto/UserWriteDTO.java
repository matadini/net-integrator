package pl.inzynier.netintegrator.user.core.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserWriteDTO {
    private final String login;

    private final String password;
}
