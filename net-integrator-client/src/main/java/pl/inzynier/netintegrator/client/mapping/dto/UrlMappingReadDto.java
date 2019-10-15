package pl.inzynier.netintegrator.client.mapping.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UrlMappingReadDto extends UrlMappingWriteDto {
    Long urlMappingId;

    public UrlMappingReadDto(Long urlMappingId, PublishEndpointDto endpoint, TargetEndpointDto target) {
        super(endpoint, target);
        this.urlMappingId = urlMappingId;
    }
}
