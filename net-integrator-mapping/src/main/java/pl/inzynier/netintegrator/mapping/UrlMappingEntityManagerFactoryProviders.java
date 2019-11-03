package pl.inzynier.netintegrator.mapping;

import pl.inzynier.netintegrator.db.util.EntityManagerFactoryProvider;

public class UrlMappingEntityManagerFactoryProviders {

    public static EntityManagerFactoryProvider providerH2() {
        return new UrlMappingEntityManagerFactoryProviderH2();
    }
}
