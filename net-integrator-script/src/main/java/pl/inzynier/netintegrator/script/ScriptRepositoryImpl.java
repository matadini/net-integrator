package pl.inzynier.netintegrator.script;

import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.db.util.JpaRepositoryUtil;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
class ScriptRepositoryImpl implements ScriptRepository {

    private final EntityManager entityManager;


    @Override
    public List<Script> findByUrlMappingId(Long parentId) {
        return null;
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
