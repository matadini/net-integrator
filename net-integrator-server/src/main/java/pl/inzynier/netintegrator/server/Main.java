package pl.inzynier.netintegrator.server;

import org.pmw.tinylog.Logger;
import pl.inzynier.netintegrator.db.util.DatabaseConfiguration;
import pl.inzynier.netintegrator.server.configuration.Configuration;
import pl.inzynier.netintegrator.server.configuration.ConfigurationRepository;

public class Main {

    public static void main(String[] args) {

        // create file

        DatabaseConfiguration mappingDbConfig = new DatabaseConfiguration();
        mappingDbConfig.setAddress("localhost-1");
        mappingDbConfig.setUser("user-2");
        mappingDbConfig.setPassword("password-3");

        Configuration configuration = new Configuration();
        configuration.setMappingDatabaseConfig(mappingDbConfig);

        ConfigurationRepository repository = ConfigurationRepository.create("D:/test/config.json");
        repository.createOrUpdate(configuration);

        Configuration read = repository.read();
        Logger.info(read);

    }
}
