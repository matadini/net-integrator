package pl.inzynier.netintegrator.script;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.inzynier.netintegrator.db.util.EntityManagerFactoryProvider;
import pl.inzynier.netintegrator.db.util.EntityManagerFactoryProviderUtil;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Map;

/**
 * Modul dostarcza domyslnego H2 {@link EntityManagerFactoryProvider}
 */
class ScriptEntityManagerFactoryProviderH2 implements EntityManagerFactoryProvider {

    @Override
    public EntityManagerFactory get() {
        Map<String, String> properties = EntityManagerFactoryProviderUtil.getJpaPropertiesForH2();
        return Persistence.createEntityManagerFactory("SCRIPT_PERSISTANCE", properties);
    }


}
