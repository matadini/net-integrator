package pl.inzynier.netintegrator.script.core;

import groovy.lang.GroovyShell;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import pl.inzynier.netintegrator.db.util.EntityManagerFactoryProvider;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScriptServiceFactory {

    public static ScriptService create(EntityManagerFactoryProvider provider) {
        EntityManagerFactory entityManagerFactoryH2 = provider.get();
        EntityManager entityManager = entityManagerFactoryH2.createEntityManager();
        ScriptRepository scriptRepository = new ScriptRepositoryImpl(entityManager);
        ModelMapper modelMapper = new ModelMapper();
        GroovyShell groovyShell = new GroovyShell();
        return new ScriptServiceImpl(scriptRepository, modelMapper, groovyShell);
    }
}
