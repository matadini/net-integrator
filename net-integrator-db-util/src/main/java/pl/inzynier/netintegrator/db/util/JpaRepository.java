package pl.inzynier.netintegrator.db.util;

import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

public interface JpaRepository<T, PK> {

    T save(T entity);

    Optional<T> findById(PK id);

    void delete(T entity);

    List<T> findAll();

    void deleteAll();

    PK count();

}

