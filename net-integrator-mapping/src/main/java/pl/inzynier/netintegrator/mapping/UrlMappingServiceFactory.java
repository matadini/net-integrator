package pl.inzynier.netintegrator.mapping;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import pl.inzynier.netintegrator.db.util.DatabaseConfiguration;

import javax.persistence.EntityManagerFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UrlMappingServiceFactory {
    public static UrlMappingService create(DatabaseConfiguration databaseConfiguration) {

        EntityManagerFactory entityManagerFactory = EntityManagerFactoryProvider.createEntityManagerFactoryH2();
        ModelMapper modelMapper = new ModelMapper();
        UrlMappingRepository repository = new UrlMappingRepositoryImpl(entityManagerFactory.createEntityManager());
        return new UrlMappingServiceImpl(modelMapper, repository);
    }
}
