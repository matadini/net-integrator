package pl.inzynier.netintegrator.client.mapping.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class UrlMappingReadDto extends UrlMappingWriteDto {

    @NonNull
    Long urlMappingId;

    public UrlMappingReadDto(Long urlMappingId, PublishEndpointDto endpoint, TargetEndpointDto target) {
        super(endpoint, target);
        this.urlMappingId = urlMappingId;
    }
}
