package pl.inzynier.netintegrator.server.configuration;


import lombok.Data;
import pl.inzynier.netintegrator.db.util.DatabaseConfiguration;

@Data
public class Configuration {

    final DatabaseConfiguration mappingDatabaseConfig;
}


