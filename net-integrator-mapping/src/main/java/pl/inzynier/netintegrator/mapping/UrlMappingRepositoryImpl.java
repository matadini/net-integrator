package pl.inzynier.netintegrator.mapping;

import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.db.util.JpaRepositoryUtil;
import pl.inzynier.netintegrator.http.util.RequestMethod;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
class UrlMappingRepositoryImpl implements UrlMappingRepository {

    private final EntityManager entityManager;


    @Override
    public Optional<UrlMapping> findByPublishUrlAndPublishMethod(String url, RequestMethod mapping) {
        return Optional.empty();
    }

    @Override
    public UrlMapping save(UrlMapping entity) {
        return JpaRepositoryUtil.merge(entity, entityManager);
    }

    @Override
    public Optional<UrlMapping> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(UrlMapping entity) {

    }

    @Override
    public List<UrlMapping> findAll() {

        String sql = "select u from UrlMapping u order by u.urlMappingId ";
        TypedQuery<UrlMapping> query = entityManager.createQuery(sql, UrlMapping.class);
        return query.getResultList();
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Long count() {
        return null;
    }
}
