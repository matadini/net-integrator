package pl.inzynier.netintegrator.mapping;


import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.db.util.JpaRepository;
import pl.inzynier.netintegrator.http.util.RequestMethod;


import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {

    /*@Query("select u from UrlMapping u where u.endpoint.methodUrl = :url and u.endpoint.method = :mapping")*/

    Optional<UrlMapping> findByPublishUrlAndPublishMethod(String url, RequestMethod mapping);
}

