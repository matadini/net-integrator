package pl.inzynier.netintegrator.mapping.core;

import org.modelmapper.ModelMapper;
import pl.inzynier.netintegrator.http.util.RequestMethod;
import pl.inzynier.netintegrator.mapping.core.dto.UrlMappingReadDto;
import pl.inzynier.netintegrator.mapping.core.dto.UrlMappingServiceException;
import pl.inzynier.netintegrator.mapping.core.dto.UrlMappingWriteDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


class UrlMappingServiceImpl implements UrlMappingService {

    private final ModelMapper modelMapper;
    private final UrlMappingRepository repository;

    UrlMappingServiceImpl(ModelMapper modelMapper, UrlMappingRepository repository) {
        super();
        this.modelMapper = modelMapper;
        this.repository = repository;
    }

    @Override
    public Long create(UrlMappingWriteDto mappingDto) throws UrlMappingServiceException {
        PublishEndpoint publishEndpoint0 = modelMapper.map(mappingDto.getEndpoint(), PublishEndpoint.class);
        TargetEndpoint targetEndpoint0 = modelMapper.map(mappingDto.getTarget(), TargetEndpoint.class);
        UrlMapping mapping0 = new UrlMapping(publishEndpoint0, targetEndpoint0);
        UrlMapping save = repository.save(mapping0);
        return save.getUrlMappingId();
    }

    @Override
    public void update(UrlMappingWriteDto mappingDto, Long urlMappingId) throws UrlMappingServiceException {

        Optional<UrlMapping> byId = repository.findById(urlMappingId);
        if (byId.isPresent()) {

            PublishEndpoint publishEndpoint0 = modelMapper.map(mappingDto.getEndpoint(), PublishEndpoint.class);
            TargetEndpoint targetEndpoint0 = modelMapper.map(mappingDto.getTarget(), TargetEndpoint.class);

            UrlMapping urlMapping = byId.get();
            urlMapping.setEndpoint(publishEndpoint0);
            urlMapping.setTarget(targetEndpoint0);
            repository.save(urlMapping);

        }

    }

    @Override
    public Optional<UrlMappingReadDto> findByPublishUrlAndPublishMethod(String url, RequestMethod mapping)
            throws UrlMappingServiceException {

        Optional<UrlMapping> find = repository.findByPublishUrlAndPublishMethod(url, mapping);
        return find.map(urlMapping -> modelMapper.map(urlMapping, UrlMappingReadDto.class));
    }

    @Override
    public void delete(Long urlMappingId) throws UrlMappingServiceException {

        Optional<UrlMapping> byId = repository.findById(urlMappingId);
        byId.ifPresent(repository::delete);


    }

    @Override
    public List<UrlMappingReadDto> findAll() throws UrlMappingServiceException {

        return repository.findAll()
                .stream()
                .map(item -> modelMapper.map(item, UrlMappingReadDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public UrlMappingReadDto findById(Long urlMappingId) throws UrlMappingServiceException {
        return repository.findById(urlMappingId)
                .map(item -> modelMapper.map(item, UrlMappingReadDto.class))
                .orElse(null);
    }

}
