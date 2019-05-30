package pl.inzynier.netintegrator.server.module.urlmapping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMethod;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TargetEndpointDto {

    String methodUrl;
    RequestMethod method;

    public String getFullUrl(String hostAddress) {
        return hostAddress + this.getMethodUrl();
    }
}
