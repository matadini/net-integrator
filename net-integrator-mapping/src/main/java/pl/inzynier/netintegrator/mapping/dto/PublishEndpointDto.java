package pl.inzynier.netintegrator.mapping.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.inzynier.netintegrator.http.util.RequestMethod;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PublishEndpointDto {

    String methodUrl;
    RequestMethod method;
}
