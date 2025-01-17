package pl.inzynier.netintegrator.mapping.core;

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

        UrlMapping singleResult = null;
        try {
            String sql = "select u from UrlMapping u where u.endpoint.methodUrl = :url and u.endpoint.method = :mapping";
            TypedQuery<UrlMapping> query = entityManager.createQuery(sql, UrlMapping.class);
            query.setParameter("url", url);
            query.setParameter("mapping", mapping);
            singleResult = query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Optional.ofNullable(singleResult);
    }

    @Override
    public UrlMapping save(UrlMapping entity) {
        return JpaRepositoryUtil.merge(entity, entityManager);
    }

    @Override
    public Optional<UrlMapping> findById(Long id) {
        try {
            String sql = "select u from UrlMapping u where u.urlMappingId = :id";
            TypedQuery<UrlMapping> query = entityManager.createQuery(sql, UrlMapping.class);
            query.setParameter("id", id);
            return Optional.ofNullable(query.getSingleResult());
        } catch (Exception e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    @Override
    public void delete(UrlMapping entity) {
        try {
            JpaRepositoryUtil.delete(entity, entityManager);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
