package pl.inzynier.netintegrator.user.entitymanagerprovider;

import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.db.util.DatabaseConfiguration;
import pl.inzynier.netintegrator.db.util.EntityManagerFactoryProvider;
import pl.inzynier.netintegrator.db.util.EntityManagerFactoryProviderUtil;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Map;

@RequiredArgsConstructor
class UserEntityManagerFactoryProviderPostgres implements EntityManagerFactoryProvider {

    private final DatabaseConfiguration configuration;

    @Override
    public EntityManagerFactory get() {
        Map<String, String> properties = EntityManagerFactoryProviderUtil.getJpaPropertiesForPostgreSql(configuration);
        return Persistence.createEntityManagerFactory("USER_PERSISTANCE", properties);
    }

}
