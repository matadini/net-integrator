package pl.inzynier.netintegrator.client.mapping;

import com.google.common.collect.Lists;
import pl.inzynier.netintegrator.client.mapping.dto.*;
import pl.inzynier.netintegrator.http.util.RequestMethod;

import java.util.List;

class UrlMappingClientStub implements UrlMappingClient {

    @Override
    public Long addUrlMapping(UrlMappingWriteDto mappingDto) throws UrlMappingClientException {
        return null;
    }

    @Override
    public void deactivateUrlMapping(Long urlMappingId) throws UrlMappingClientException {

    }

    @Override
    public List<UrlMappingReadDto> findAll() throws UrlMappingClientException {

        // mapowanie 1
        final PublishEndpointDto publishEndpoint0 = new PublishEndpointDto("/api-get", RequestMethod.GET);
        final TargetEndpointDto targetEndpoint0 = new TargetEndpointDto("/fake-get", RequestMethod.GET, "http://localhost:9090");
        final UrlMappingReadDto mapping1 = new UrlMappingReadDto(1L, publishEndpoint0, targetEndpoint0);

        // mapowanie 3
        final PublishEndpointDto publishEndpoint1 = new PublishEndpointDto("/api-post-xml", RequestMethod.POST);
        final TargetEndpointDto targetEndpoint1 = new TargetEndpointDto("/fake-post", RequestMethod.POST, "http://localhost:9090");
        final UrlMappingReadDto mapping2 = new UrlMappingReadDto(2L, publishEndpoint1, targetEndpoint1);

        // mapowanie 3
        final PublishEndpointDto publishEndpoint2 = new PublishEndpointDto("/api-post", RequestMethod.POST);
        final TargetEndpointDto targetEndpoint2 = new TargetEndpointDto("/fake-post", RequestMethod.POST, "http://localhost:9090");
        final UrlMappingReadDto mapping3 = new UrlMappingReadDto(3L, publishEndpoint2, targetEndpoint2);

        return Lists.newArrayList(mapping1, mapping2, mapping3);
    }
}
