package pl.inzynier.netintegrator.user.dto;

import lombok.*;


@Getter
@EqualsAndHashCode(callSuper = false)
public class UserReadDTO  extends UserWriteDTO {

   private final Long userId;

    public UserReadDTO(Long userId, String login, String passwordMD5) {
        super(login, passwordMD5);
        this.userId = userId;
    }
}

