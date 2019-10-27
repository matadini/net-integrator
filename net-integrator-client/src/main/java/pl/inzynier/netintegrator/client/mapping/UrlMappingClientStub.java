package pl.inzynier.netintegrator.client.mapping;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import pl.inzynier.netintegrator.client.mapping.dto.*;
import pl.inzynier.netintegrator.http.util.RequestMethod;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

class UrlMappingClientStub implements UrlMappingClient {

    private final AtomicLong sequence = new AtomicLong(0);

    private final Map<Long, UrlMappingReadDto> database = Maps.newHashMap();

    UrlMappingClientStub() {


        try {
            this.create(new UrlMappingWriteDto(
                    new PublishEndpointDto("/api-get", RequestMethod.GET),
                    new TargetEndpointDto("/fake-get", RequestMethod.GET, "http://localhost:9090")));

            this.create(new UrlMappingWriteDto(
                    new PublishEndpointDto("/api-post-xml", RequestMethod.POST),
                    new TargetEndpointDto("/fake-post", RequestMethod.POST, "http://localhost:9090")));

            this.create(new UrlMappingWriteDto(
                    new PublishEndpointDto("/api-post", RequestMethod.POST),
                    new TargetEndpointDto("/fake-post", RequestMethod.POST, "http://localhost:9090")));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


    @Override
    public Long create(UrlMappingWriteDto dto) throws UrlMappingClientException {

        long andIncrement = sequence.incrementAndGet();
        UrlMappingReadDto value = new UrlMappingReadDto(andIncrement, dto.getEndpoint(), dto.getTarget());
        database.put(andIncrement, value);
        return andIncrement;
    }

    @Override
    public void update(UrlMappingReadDto dto) throws UrlMappingClientException {

        if (!database.containsKey(dto.getUrlMappingId())) {
            this.create(new UrlMappingWriteDto(dto.getEndpoint(), dto.getTarget()));
        } else {
            database.replace(dto.getUrlMappingId(), dto);
        }

    }


    @Override
    public List<UrlMappingReadDto> findAll() throws UrlMappingClientException {
        return Lists.newArrayList(database.values());
    }

    @Override
    public void deactivate(Long urlMappingId) throws UrlMappingClientException {

        if (database.containsKey(urlMappingId)) {
            database.remove(urlMappingId);
        }
    }
}
