package pl.inzynier.netintegrator.server.module.script;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

interface ScriptRepository extends JpaRepository<Script, Long> {

    @Query("select s from Script s where s.urlMappingParent.urlMappingParentId = :parentId")
    List<Script> findByUrlMappingParentId(@Param("parentId") Long parentId);

}
