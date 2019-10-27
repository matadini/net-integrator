package pl.inzynier.netintegrator.db.util;

import com.google.common.collect.Maps;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

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
}
