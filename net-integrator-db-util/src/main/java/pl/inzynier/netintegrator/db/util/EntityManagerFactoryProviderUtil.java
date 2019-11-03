package pl.inzynier.netintegrator.db.util;

import com.google.common.collect.Maps;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.EntityManagerFactory;
import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EntityManagerFactoryProviderUtil {
    public static Map<String, String> getJpaPropertiesForH2() {
        Map<String, String> properties = Maps.newHashMap();
        properties.put("javax.persistence.jdbc.driver", "org.h2.Driver");
        properties.put("javax.persistence.jdbc.url", "jdbc:h2:mem:test");
        properties.put("javax.persistence.jdbc.user", "sa");
        properties.put("javax.persistence.jdbc.password", "");
        properties.put("hibernate.hbm2ddl.auto", "create-drop");
        properties.put("show_sql", "true");
        properties.put("format_sql", "true");
        properties.put("use_sql_comments", "true");
        return properties;
    }
    public static Map<String, String> getJpaPropertiesForPostgreSql(DatabaseConfiguration configuration) {
        String address = configuration.getAddress();
        String password = configuration.getPassword();
        String user = configuration.getUser();

        Map<String, String> prop = new HashMap<>();
        prop.put("javax.persistence.jdbc.url", "jdbc:postgresql://" + address + "/archiwum");
        prop.put("javax.persistence.jdbc.password", password);
        prop.put("javax.persistence.jdbc.user", user);
        prop.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");
        prop.put("hibernate.hbm2ddl.auto", "create-drop");
        prop.put("show_sql", "true");
        prop.put("format_sql", "true");
        prop.put("use_sql_comments", "true");
        return prop;
    }
}
