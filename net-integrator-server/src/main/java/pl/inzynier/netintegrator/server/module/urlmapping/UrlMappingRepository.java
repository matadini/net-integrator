package pl.inzynier.netintegrator.server.module.urlmapping;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.RequestMethod;

interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {

    @Query("select u from UrlMapping u where u.endpoint.methodUrl = :url and u.endpoint.method = :mapping")
    Optional<UrlMapping> findByPublishUrlAndPublishMethod(@Param("url") String url, @Param("mapping") RequestMethod mapping);
}
