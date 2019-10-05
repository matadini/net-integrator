package pl.inzynier.netintegrator.mapping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.inzynier.netintegrator.http.util.RequestMethod;


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
