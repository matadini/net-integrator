package pl.inzynier.netintegrator.mapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import pl.inzynier.netintegrator.http.util.RequestMethod;
import pl.inzynier.netintegrator.mapping.dto.UrlMappingReadDto;
import pl.inzynier.netintegrator.mapping.dto.UrlMappingServiceException;
import pl.inzynier.netintegrator.mapping.dto.UrlMappingWriteDto;


class UrlMappingServiceImpl implements UrlMappingService {

	private final ModelMapper modelMapper;
	private final UrlMappingRepository repository;
//	private final ScriptService scriptService;


	public UrlMappingServiceImpl(ModelMapper modelMapper, UrlMappingRepository repository) {
		super();
		this.modelMapper = modelMapper;
		this.repository = repository;
	}

	@Override
	public Long addUrlMapping(UrlMappingWriteDto mappingDto) throws UrlMappingServiceException {
		PublishEndpoint publishEndpoint0 =modelMapper.map(mappingDto.getEndpoint(), PublishEndpoint.class);
		TargetEndpoint targetEndpoint0 = modelMapper.map(mappingDto.getTarget(), TargetEndpoint.class);
		UrlMapping mapping0 = new UrlMapping(publishEndpoint0, targetEndpoint0);
		UrlMapping save = repository.save(mapping0);
		return save.getUrlMappingId();
	}

	@Override
	public Optional<UrlMappingReadDto> findByPublishUrlAndPublishMethod(String url, RequestMethod mapping)
			throws UrlMappingServiceException {

		Optional<UrlMapping> find = repository.findByPublishUrlAndPublishMethod(url, mapping);
		return find.isPresent() ? Optional.of(modelMapper.map(find.get(), UrlMappingReadDto.class)) : Optional.empty();
	}

	@Override
	public void deactivateUrlMapping(Long urlMappingId) throws UrlMappingServiceException {

	}

	@Override
	public List<UrlMappingReadDto> findAll() throws UrlMappingServiceException {

		return repository.findAll()
				.stream()
				.map(item -> modelMapper.map(item, UrlMappingReadDto.class))
				.collect(Collectors.toList());
	}

//	@Override
//	public void demoInit() throws UrlMappingServiceException {
//
//		try {
//			// 0
//			PublishEndpoint publishEndpoint0 = new PublishEndpoint("/api-get", RequestMethod.GET);
//
//			TargetEndpoint targetEndpoint0 = new TargetEndpoint("/fake-get", RequestMethod.GET);
//
//			UrlMapping mapping0 = new UrlMapping(publishEndpoint0, targetEndpoint0);
//			repository.save(mapping0);
//
//			// 1
//			PublishEndpoint publishEndpoint1 = new PublishEndpoint("/api-post-xml", RequestMethod.POST);
//
//			TargetEndpoint targetEndpoint1 = new TargetEndpoint("/fake-post", RequestMethod.POST);
//
//			UrlMapping mapping1 = new UrlMapping(publishEndpoint1, targetEndpoint1);
//			UrlMapping save1 = repository.save(mapping1);
//			String scriptJsonToXml = ExampleGroovyScript.createExampleGroovyScriptJsonToXml();
//			scriptService.addScript(save1.getUrlMappingId(), ScriptType.POST_CALL, scriptJsonToXml);
//
//			// 2
//			PublishEndpoint publishEndpoint2 = new PublishEndpoint("/api-post", RequestMethod.POST);
//
//			TargetEndpoint targetEndpoint2 = new TargetEndpoint("/fake-post", RequestMethod.POST);
//
//			UrlMapping mapping2 = new UrlMapping(publishEndpoint2, targetEndpoint2);
//			UrlMapping save = repository.save(mapping2);
//			String scriptJsonUpper = ExampleGroovyScript.createExampleGroovyScriptToUpperCase();
//			scriptService.addScript(save.getUrlMappingId(), ScriptType.POST_CALL, scriptJsonUpper);
//		} catch (Exception e) {
//			throw new UrlMappingServiceException(e);
//		}
//	}

}
