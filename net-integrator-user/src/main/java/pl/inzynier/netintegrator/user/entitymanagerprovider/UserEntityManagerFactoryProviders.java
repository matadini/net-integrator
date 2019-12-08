package pl.inzynier.netintegrator.user.entitymanagerprovider;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.inzynier.netintegrator.db.util.DatabaseConfiguration;
import pl.inzynier.netintegrator.db.util.EntityManagerFactoryProvider;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserEntityManagerFactoryProviders {

    public static EntityManagerFactoryProvider providerH2() {
        return new UserEntityManagerFactoryProviderH2();
    }

    public static EntityManagerFactoryProvider providerPostgres(DatabaseConfiguration configuration) {
        return new UserEntityManagerFactoryProviderPostgres(configuration);
    }
}
