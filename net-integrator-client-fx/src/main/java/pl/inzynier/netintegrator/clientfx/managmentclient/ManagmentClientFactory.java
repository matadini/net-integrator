package pl.inzynier.netintegrator.clientfx.managmentclient;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.inzynier.netintegrator.clientfx.managmentclient.dto.ManagmentClientException;
import pl.inzynier.netintegrator.clientfx.managmentclient.dto.UrlMappingRead;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ManagmentClientFactory {

    public static ManagmentClient create(String address) {
        return new ManagmentClientFakeImpl();
    }
}

