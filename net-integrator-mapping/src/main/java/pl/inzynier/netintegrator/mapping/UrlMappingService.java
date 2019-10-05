package pl.inzynier.netintegrator.mapping;

import org.modelmapper.ModelMapper;
import pl.inzynier.netintegrator.db.util.DatabaseConfiguration;
import pl.inzynier.netintegrator.http.util.RequestMethod;
import pl.inzynier.netintegrator.mapping.dto.UrlMappingReadDto;
import pl.inzynier.netintegrator.mapping.dto.UrlMappingWriteDto;

import javax.persistence.EntityManagerFactory;
import java.util.List;
import java.util.Optional;


public interface UrlMappingService {

    Long addUrlMapping(UrlMappingWriteDto mappingDto) throws UrlMappingServiceException;

    Optional<UrlMappingReadDto> findByPublishUrlAndPublishMethod(String url, RequestMethod mapping) throws UrlMappingServiceException;

    void deactivateUrlMapping(Long urlMappingId) throws UrlMappingServiceException;

    List<UrlMappingReadDto> findAll() throws UrlMappingServiceException;

    static UrlMappingService create(DatabaseConfiguration databaseConfiguration) {

        EntityManagerFactory entityManagerFactory = EntityManagerFactoryProvider.createEntityManagerFactoryH2();
        ModelMapper modelMapper = new ModelMapper();
        UrlMappingRepository repository = new UrlMappingRepositoryImpl(entityManagerFactory.createEntityManager());
        return new UrlMappingServiceImpl(modelMapper, repository);
    }
}
