package pl.inzynier.netintegrator.mapping.core;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import pl.inzynier.netintegrator.db.util.EntityManagerFactoryProvider;

import javax.persistence.EntityManagerFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UrlMappingServiceFactory {
    public static UrlMappingService create(EntityManagerFactoryProvider provider) {
        EntityManagerFactory entityManagerFactory = provider.get();
        ModelMapper modelMapper = new ModelMapper();
        UrlMappingRepository repository = new UrlMappingRepositoryImpl(entityManagerFactory.createEntityManager());
        return new UrlMappingServiceImpl(modelMapper, repository);
    }
}
