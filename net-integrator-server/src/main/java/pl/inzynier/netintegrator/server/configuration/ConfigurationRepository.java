package pl.inzynier.netintegrator.server.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public interface ConfigurationRepository {

    boolean createOrUpdate(Configuration entity);

    Configuration read();


   static ConfigurationRepository create(String file) {
        Gson gson = new GsonBuilder().serializeNulls().create();
        return new ConfigurationRepositoryJsonImpl(gson, file);
    }
}
