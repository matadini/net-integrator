package pl.inzynier.netintegrator.desktop.gui.mapping;

import pl.inzynier.netintegrator.client.mapping.dto.PublishEndpointDto;
import pl.inzynier.netintegrator.client.mapping.dto.TargetEndpointDto;
import pl.inzynier.netintegrator.client.mapping.dto.UrlMappingWriteDto;

import java.util.function.Function;

class UrlMappingPaneAddModelToUrlMappingWriteDto implements Function<UrlMappingPaneAddModel, UrlMappingWriteDto> {


    @Override
    public UrlMappingWriteDto apply(UrlMappingPaneAddModel model) {
        TargetEndpointDto target = new TargetEndpointDto();
        target.setHostAddress(model.targetServer.get());
        target.setMethod(model.targetMethod.get());
        target.setMethodUrl(model.targetURL.get());

        PublishEndpointDto endpoint = new PublishEndpointDto();
        endpoint.setMethod(model.publishMethod.get());
        endpoint.setMethodUrl(model.publishURL.get());

        UrlMappingWriteDto mappingDto = new UrlMappingWriteDto();
        mappingDto.setEndpoint(endpoint);
        mappingDto.setTarget(target);
        return mappingDto;
    }
}
