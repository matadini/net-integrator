package pl.inzynier.netintegrator.db.util;

import com.google.common.collect.Maps;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EntityManagerFactoryProvider {

    public static EntityManagerFactory createEntityManagerFactoryH2() {

        Map<String, String> properties = Maps.newHashMap();
        properties.put("javax.persistence.jdbc.driver", "org.h2.Driver");
        properties.put("javax.persistence.jdbc.url", "jdbc:h2:mem:test");
        properties.put("javax.persistence.jdbc.user", "sa");
        properties.put("javax.persistence.jdbc.password", "");
        properties.put("show_sql", "true");
        properties.put("eclipselink.ddl-generation", "create-tables");
        properties.put("eclipselink.ddl-generation.output-mode", "database");

        properties.put("show_sql", "true");
        properties.put("format_sql", "true");
        properties.put("use_sql_comments", "true");

        return Persistence.createEntityManagerFactory("H2PU", properties);
    }
}
