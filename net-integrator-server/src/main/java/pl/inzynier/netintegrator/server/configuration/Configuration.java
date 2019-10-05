package pl.inzynier.netintegrator.server.configuration;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import pl.inzynier.netintegrator.db.util.DatabaseConfiguration;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Configuration {

    private DatabaseConfiguration mappingDatabaseConfig;
}


