package pl.inzynier.netintegrator.server.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public interface ConfigurationRepository {

    boolean createOrUpdate(Configuration entity);

    Configuration read();


   static ConfigurationRepository create(Gson gson, String file) {
        return new ConfigurationRepositoryJsonImpl(gson, file);
    }
}
