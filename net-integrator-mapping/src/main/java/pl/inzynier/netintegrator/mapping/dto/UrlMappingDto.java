package pl.inzynier.netintegrator.mapping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlMappingDto {
    Long urlMappingId;
    PublishEndpointDto endpoint;
    TargetEndpointDto target;
}
