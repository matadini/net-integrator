package pl.inzynier.netintegrator.mapping;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import pl.inzynier.netintegrator.mapping.dto.UrlMappingDto;

@Service
public interface UrlMappingService {
	Optional<UrlMappingDto> findByPublishUrlAndPublishMethod(String url, RequestMethod mapping)
			throws UrlMappingServiceException;

	List<UrlMappingDto> findAll() throws UrlMappingServiceException;

	void demoInit() throws UrlMappingServiceException;

}
