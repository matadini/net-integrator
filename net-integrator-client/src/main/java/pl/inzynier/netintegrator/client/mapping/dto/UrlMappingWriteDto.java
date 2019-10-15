package pl.inzynier.netintegrator.client.mapping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlMappingWriteDto {
    PublishEndpointDto endpoint;
    TargetEndpointDto target;
}
