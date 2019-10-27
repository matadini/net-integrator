package pl.inzynier.netintegrator.client.mapping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import pl.inzynier.netintegrator.http.util.RequestMethod;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class TargetEndpointDto {

    @NonNull
    String methodUrl;

    @NonNull
    RequestMethod method;

    @NonNull
    String hostAddress;
}
