package pl.inzynier.netintegrator.script;


import pl.inzynier.netintegrator.db.util.JpaRepository;

import java.util.List;

interface ScriptRepository extends JpaRepository<Script, Long> {

    List<Script> findByUrlMappingId(Long urlMappingId);

}

