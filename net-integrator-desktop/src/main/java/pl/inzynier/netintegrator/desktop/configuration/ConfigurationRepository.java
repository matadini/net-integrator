package pl.inzynier.netintegrator.desktop.configuration;

import com.google.gson.Gson;

public interface ConfigurationRepository {

    boolean createOrUpdate(Configuration entity);

    Configuration read();

    static ConfigurationRepository create(
            Gson gson, String file) {
        return new ConfigurationRepositoryJsonImpl(gson, file);
    }
}
