package pl.inzynier.netintegrator.user.entitymanagerprovider;

import pl.inzynier.netintegrator.db.util.EntityManagerFactoryProvider;
import pl.inzynier.netintegrator.db.util.EntityManagerFactoryProviderUtil;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Map;


class UserEntityManagerFactoryProviderH2 implements EntityManagerFactoryProvider {

    @Override
    public EntityManagerFactory get() {
        Map<String, String> properties = EntityManagerFactoryProviderUtil.getJpaPropertiesForH2();
        return Persistence.createEntityManagerFactory("USER_PERSISTANCE", properties);
    }

}

