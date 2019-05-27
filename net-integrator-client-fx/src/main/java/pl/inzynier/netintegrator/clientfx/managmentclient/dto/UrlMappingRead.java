package pl.inzynier.netintegrator.clientfx.managmentclient.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.modelmapper.internal.bytebuddy.asm.Advice;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UrlMappingRead {

    Long urlMappingId;
    String publishURL;
    String publishMethod;

    String targetURL;
    String targetMethod;
    String targetServer;

}
