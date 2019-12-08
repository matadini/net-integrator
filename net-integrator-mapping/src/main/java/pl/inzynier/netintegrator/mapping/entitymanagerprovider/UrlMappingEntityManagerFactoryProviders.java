package pl.inzynier.netintegrator.mapping.entitymanagerprovider;

import pl.inzynier.netintegrator.db.util.DatabaseConfiguration;
import pl.inzynier.netintegrator.db.util.EntityManagerFactoryProvider;

public class UrlMappingEntityManagerFactoryProviders {

    public static EntityManagerFactoryProvider providerH2() {
        return new UrlMappingEntityManagerFactoryProviderH2();
    }

    public static EntityManagerFactoryProvider providerPostgres(DatabaseConfiguration configuration) {
        return new UrlMappingEntityManagerFactoryProviderPostgres(configuration);
    }

}
