package pl.inzynier.netintegrator.script;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.inzynier.netintegrator.db.util.DatabaseConfiguration;
import pl.inzynier.netintegrator.db.util.EntityManagerFactoryProvider;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScriptEntityManagerFactoryProviders {
    public static EntityManagerFactoryProvider providerH2() {
        return new ScriptEntityManagerFactoryProviderH2();
    }

    public static EntityManagerFactoryProvider providerPostgres(DatabaseConfiguration configuration) {
        return new ScriptEntityManagerFactoryProviderPostgres(configuration);
    }
}
