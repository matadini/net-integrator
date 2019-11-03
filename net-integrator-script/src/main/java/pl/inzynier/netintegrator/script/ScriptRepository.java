package pl.inzynier.netintegrator.script;


import pl.inzynier.netintegrator.db.util.JpaRepository;

import java.util.List;

interface ScriptRepository extends JpaRepository<Script, Long> {

   // @Query("select s from Script s where s.urlMappingParentId = :parentId")
    List<Script> findByUrlMappingId(Long urlMappingId);

}

