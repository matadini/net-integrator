package pl.inzynier.netintegrator.script;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.inzynier.netintegrator.db.util.DatabaseConfiguration;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ScriptServiceFactory {

    public static ScriptService create(DatabaseConfiguration databaseConfiguration) {
        return null;
    }
}
