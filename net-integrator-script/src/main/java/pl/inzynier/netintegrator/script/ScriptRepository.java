package pl.inzynier.netintegrator.script;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
interface ScriptRepository extends JpaRepository<Script, Long> {

    @Query("select s from Script s where s.urlMappingParentId = :parentId")
    List<Script> findByUrlMappingParentId(@Param("parentId") Long parentId);

}
