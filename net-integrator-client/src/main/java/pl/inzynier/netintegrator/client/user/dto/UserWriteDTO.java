package pl.inzynier.netintegrator.client.user.dto;

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
