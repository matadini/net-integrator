package pl.inzynier.netintegrator.script.core;

import pl.inzynier.netintegrator.script.core.dto.ScriptReadDto;
import pl.inzynier.netintegrator.script.core.dto.ScriptServiceException;
import pl.inzynier.netintegrator.script.core.dto.ScriptType;
import pl.inzynier.netintegrator.script.core.dto.ScriptWriteDto;

import java.util.List;


public interface ScriptService {

    Long create(ScriptWriteDto dto) throws ScriptServiceException;

    List<ScriptReadDto> findByUrlMappingId(Long urlMappingId) throws ScriptServiceException;

    void delete(Long scriptId)  throws ScriptServiceException;

    String executeScripts(Long urlMappingId, String httpRequestContent, ScriptType scriptType)  throws ScriptServiceException;
}

