package pl.inzynier.netintegrator.clientfx.managmentclient;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ManagmentClientFactory {

    public static ManagmentClient create(String address) {
        return ManagmentClientImpl.builder()

                .build();
    }
}
