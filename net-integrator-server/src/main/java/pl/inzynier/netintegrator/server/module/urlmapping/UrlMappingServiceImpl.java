package pl.inzynier.netintegrator.server.module.urlmapping;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.inzynier.netintegrator.script.ScriptService;
import pl.inzynier.netintegrator.script.dto.ScriptType;
import pl.inzynier.netintegrator.server.module.urlmapping.dto.UrlMappingDto;

@Component
class UrlMappingServiceImpl implements UrlMappingService {

	private final ModelMapper modelMapper;
	private final UrlMappingRepository repository;
	private final ScriptService scriptService;

	@Autowired
	public UrlMappingServiceImpl(ModelMapper modelMapper, UrlMappingRepository repository,
			ScriptService scriptService) {
		super();
		this.modelMapper = modelMapper;
		this.repository = repository;
		this.scriptService = scriptService;
	}

	@Override
	public Optional<UrlMappingDto> findByPublishUrlAndPublishMethod(String url, RequestMethod mapping)
			throws UrlMappingServiceException {

		Optional<UrlMapping> find = repository.findByPublishUrlAndPublishMethod(url, mapping);
		return find.isPresent() ? Optional.of(modelMapper.map(find.get(), UrlMappingDto.class)) : Optional.empty();
	}

	@Override
	public List<UrlMappingDto> findAll() throws UrlMappingServiceException {
		List<UrlMapping> findAll = repository.findAll();
		return findAll.stream().map(item -> modelMapper.map(item, UrlMappingDto.class)).collect(Collectors.toList());
	}

	@Override
	public void demoInit() throws UrlMappingServiceException {

		try {
			// 0
			PublishEndpoint publishEndpoint0 = new PublishEndpoint("/api-get", RequestMethod.GET);

			TargetEndpoint targetEndpoint0 = new TargetEndpoint("/fake-get", RequestMethod.GET);

			UrlMapping mapping0 = new UrlMapping(publishEndpoint0, targetEndpoint0);
			repository.save(mapping0);

			// 1
			PublishEndpoint publishEndpoint1 = new PublishEndpoint("/api-post-xml", RequestMethod.POST);

			TargetEndpoint targetEndpoint1 = new TargetEndpoint("/fake-post", RequestMethod.POST);

			UrlMapping mapping1 = new UrlMapping(publishEndpoint1, targetEndpoint1);
			UrlMapping save1 = repository.save(mapping1);
			String scriptJsonToXml = ExampleGroovyScript.createExampleGroovyScriptJsonToXml();
			scriptService.addScript(save1.getUrlMappingId(), ScriptType.POST_CALL, scriptJsonToXml);

			// 2
			PublishEndpoint publishEndpoint2 = new PublishEndpoint("/api-post", RequestMethod.POST);

			TargetEndpoint targetEndpoint2 = new TargetEndpoint("/fake-post", RequestMethod.POST);

			UrlMapping mapping2 = new UrlMapping(publishEndpoint2, targetEndpoint2);
			UrlMapping save = repository.save(mapping2);
			String scriptJsonUpper = ExampleGroovyScript.createExampleGroovyScriptToUpperCase();
			scriptService.addScript(save.getUrlMappingId(), ScriptType.POST_CALL, scriptJsonUpper);
		} catch (Exception e) {
			throw new UrlMappingServiceException(e);
		}
	}

}
