package pl.inzynier.netintegrator.script;


import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.db.util.JpaRepository;
import pl.inzynier.netintegrator.db.util.JpaRepositoryUtil;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

interface ScriptRepository extends JpaRepository<Script, Long> {

   // @Query("select s from Script s where s.urlMappingParentId = :parentId")
    List<Script> findByUrlMappingId(Long urlMappingId);

}

