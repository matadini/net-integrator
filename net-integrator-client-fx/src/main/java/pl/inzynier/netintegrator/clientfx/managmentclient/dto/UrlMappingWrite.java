package pl.inzynier.netintegrator.clientfx.managmentclient.dto;

import lombok.Data;

@Data
public class UrlMappingWrite {

    String publishURL;
    String publishMethod;

    String targetURL;
    String targetMethod;
    String targetServer;
}
