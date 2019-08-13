package pl.inzynier.netintegrator.server.module.urlmapping;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.inzynier.netintegrator.server.module.urlmapping.dto.UrlMappingDto;

@Service
public interface UrlMappingService {
	Optional<UrlMappingDto> findByPublishUrlAndPublishMethod(String url, RequestMethod mapping)
			throws UrlMappingServiceException;

	List<UrlMappingDto> findAll() throws UrlMappingServiceException;

	void demoInit() throws UrlMappingServiceException;

}
