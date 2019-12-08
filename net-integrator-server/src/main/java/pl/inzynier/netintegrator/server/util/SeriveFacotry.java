package pl.inzynier.netintegrator.server.util;

import pl.inzynier.netintegrator.db.util.DatabaseConfiguration;
import pl.inzynier.netintegrator.db.util.EntityManagerFactoryProvider;
import pl.inzynier.netintegrator.mapping.core.UrlMappingService;
import pl.inzynier.netintegrator.mapping.core.UrlMappingServiceFactory;
import pl.inzynier.netintegrator.mapping.entitymanagerprovider.UrlMappingEntityManagerFactoryProviders;
import pl.inzynier.netintegrator.script.core.ScriptService;
import pl.inzynier.netintegrator.script.core.ScriptServiceFactory;
import pl.inzynier.netintegrator.script.entitymanagerprovider.ScriptEntityManagerFactoryProviders;
import pl.inzynier.netintegrator.user.core.UserService;
import pl.inzynier.netintegrator.user.core.UserServiceFactory;
import pl.inzynier.netintegrator.user.entitymanagerprovider.UserEntityManagerFactoryProviders;

public class SeriveFacotry {

    public static UserService getUserService(DatabaseConfiguration configuration, boolean useH2) {
        EntityManagerFactoryProvider emfProviderUser = useH2 ?
                UserEntityManagerFactoryProviders.providerH2() :
                UserEntityManagerFactoryProviders.providerPostgres(configuration);
        return UserServiceFactory.create(emfProviderUser);
    }

    public static UrlMappingService getUrlMappingService(DatabaseConfiguration configuration, boolean useH2) {
        EntityManagerFactoryProvider urlMappingProvider = useH2 ?
                UrlMappingEntityManagerFactoryProviders.providerH2() :
                UrlMappingEntityManagerFactoryProviders.providerPostgres(configuration);
        return UrlMappingServiceFactory.create(urlMappingProvider);
    }

    public static ScriptService getScriptService(DatabaseConfiguration configuration, boolean useH2) {
        EntityManagerFactoryProvider scriptProvider = useH2 ?
                ScriptEntityManagerFactoryProviders.providerH2() :
                ScriptEntityManagerFactoryProviders.providerPostgres(configuration);
        return ScriptServiceFactory.create(scriptProvider);
    }
}
