package pl.inzynier.netintegrator.db.util;

import javax.persistence.EntityManagerFactory;

public interface EntityManagerFactoryProvider {
    EntityManagerFactory get();
}
