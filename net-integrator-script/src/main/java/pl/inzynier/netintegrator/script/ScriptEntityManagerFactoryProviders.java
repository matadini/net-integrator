package pl.inzynier.netintegrator.script;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.inzynier.netintegrator.db.util.EntityManagerFactoryProvider;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScriptEntityManagerFactoryProviders {
    public static EntityManagerFactoryProvider providerH2() {
        return new ScriptEntityManagerFactoryProviderH2();
    }
}
