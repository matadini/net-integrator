package pl.inzynier.netintegrator.server.module.urlmapping;

import org.springframework.data.jpa.repository.JpaRepository;

interface UrlMappingRepository extends JpaRepository<UrlMapping, Long> {

}
