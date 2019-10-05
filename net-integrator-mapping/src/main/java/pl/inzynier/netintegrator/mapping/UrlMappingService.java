package pl.inzynier.netintegrator.mapping;

import pl.inzynier.netintegrator.http.util.RequestMethod;
import pl.inzynier.netintegrator.mapping.dto.UrlMappingDto;

import java.util.List;
import java.util.Optional;


public interface UrlMappingService {
	Optional<UrlMappingDto> findByPublishUrlAndPublishMethod(String url, RequestMethod mapping)
			throws UrlMappingServiceException;

	List<UrlMappingDto> findAll() throws UrlMappingServiceException;

	void demoInit() throws UrlMappingServiceException;

}
