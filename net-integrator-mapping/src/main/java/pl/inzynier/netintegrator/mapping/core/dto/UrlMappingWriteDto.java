package pl.inzynier.netintegrator.mapping.core.dto;

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
