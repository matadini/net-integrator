package pl.inzynier.netintegrator.mapping;

import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.db.util.DatabaseConfiguration;
import pl.inzynier.netintegrator.db.util.EntityManagerFactoryProvider;
import pl.inzynier.netintegrator.db.util.EntityManagerFactoryProviderUtil;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Map;

@RequiredArgsConstructor
class UrlMappingEntityManagerFactoryProviderPostgreSql implements EntityManagerFactoryProvider {

    private final DatabaseConfiguration configuration;

    @Override
    public EntityManagerFactory get() {
        Map<String, String> prop = EntityManagerFactoryProviderUtil.getJpaPropertiesForPostgreSql(configuration);
        return Persistence.createEntityManagerFactory("MAPPING_PERSISTANCE", prop );
    }
}
