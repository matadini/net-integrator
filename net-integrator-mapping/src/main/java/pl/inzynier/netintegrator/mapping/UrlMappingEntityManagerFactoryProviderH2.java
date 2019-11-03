package pl.inzynier.netintegrator.mapping;

import pl.inzynier.netintegrator.db.util.EntityManagerFactoryProvider;
import pl.inzynier.netintegrator.db.util.EntityManagerFactoryProviderUtil;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Map;


class UrlMappingEntityManagerFactoryProviderH2 implements EntityManagerFactoryProvider {

    @Override
    public EntityManagerFactory get() {
        Map<String, String> properties = EntityManagerFactoryProviderUtil.getJpaPropertiesForH2();
        return Persistence.createEntityManagerFactory("MAPPING_PERSISTANCE", properties);
    }
}

