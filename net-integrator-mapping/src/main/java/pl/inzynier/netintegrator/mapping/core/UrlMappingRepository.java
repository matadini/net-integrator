package pl.inzynier.netintegrator.mapping.core;


import pl.inzynier.netintegrator.db.util.JpaRepository;
import pl.inzynier.netintegrator.http.util.RequestMethod;

import java.util.Optional;

interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {

    /*@Query("select u from UrlMapping u where u.endpoint.methodUrl = :url and u.endpoint.method = :mapping")*/

    Optional<UrlMapping> findByPublishUrlAndPublishMethod(String url, RequestMethod mapping);
}

