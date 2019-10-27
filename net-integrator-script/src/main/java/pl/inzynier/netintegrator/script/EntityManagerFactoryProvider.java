package pl.inzynier.netintegrator.script;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.inzynier.netintegrator.db.util.EntityManagerFactoryProviderUtil;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class EntityManagerFactoryProvider {

    public static EntityManagerFactory createEntityManagerFactoryH2() {

        Map<String, String> properties = EntityManagerFactoryProviderUtil.getJpaPropertiesForH2();

        return Persistence.createEntityManagerFactory("SCRIPT_PERSISTANCE", properties);
    }


}
