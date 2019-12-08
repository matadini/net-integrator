package pl.inzynier.netintegrator.user.core.dto;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class UserWriteDTO {
    private final String login;

    private final String password;
}
