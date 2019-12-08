package pl.inzynier.netintegrator.user.core.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;


@Getter
@EqualsAndHashCode(callSuper = false)
public class UserReadDTO  extends UserWriteDTO {

   private final Long userId;

    public UserReadDTO(Long userId, String login, String passwordMD5) {
        super(login, passwordMD5);
        this.userId = userId;
    }
}

