package pl.inzynier.netintegrator.client.mapping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlMappingWriteDto {

    @NonNull
    PublishEndpointDto endpoint;

    @NonNull
    TargetEndpointDto target;
}
