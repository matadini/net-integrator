package pl.inzynier.netintegrator.server.module.script;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Read-only
 */
@Repository
class UrlMappingParentRepository {

    private final UrlMappingParentSpringRepository springRepository;

    @Autowired
    public UrlMappingParentRepository(UrlMappingParentSpringRepository springRepository) {
        this.springRepository = springRepository;
    }

    Optional<UrlMappingParent> findById(Long id) {
        return springRepository.findById(id);
    }

}

