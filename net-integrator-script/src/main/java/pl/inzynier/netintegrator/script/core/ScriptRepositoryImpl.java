package pl.inzynier.netintegrator.script.core;

import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.db.util.JpaRepositoryUtil;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
class ScriptRepositoryImpl implements ScriptRepository {

    private final EntityManager entityManager;


    @Override
    public List<Script> findByUrlMappingId(Long urlMappingId) {
        String sql = "select s from Script s where s.urlMappingId = :urlMappingId order by s.scriptId";
        TypedQuery<Script> query = entityManager.createQuery(sql, Script.class);
        query.setParameter("urlMappingId", urlMappingId);
        return query.getResultList();
    }

    @Override
    public Script save(Script entity) {
        return JpaRepositoryUtil.merge(entity, entityManager);
    }

    @Override
    public Optional<Script> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Script entity) {

    }

    @Override
    public List<Script> findAll() {
        String sql = "select s from Script s order by s.scriptId";
        return entityManager.createQuery(sql, Script.class).getResultList();
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Long count() {
        return null;
    }
}
