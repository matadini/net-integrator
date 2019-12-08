package pl.inzynier.netintegrator.script.core;

import pl.inzynier.netintegrator.script.core.dto.ScriptReadDto;
import pl.inzynier.netintegrator.script.core.dto.ScriptServiceException;
import pl.inzynier.netintegrator.script.core.dto.ScriptWriteDto;

import java.util.List;


public interface ScriptService {

    Long addScript(ScriptWriteDto dto) throws ScriptServiceException;

    List<ScriptReadDto> findByUrlMappingId(Long urlMappingId) throws ScriptServiceException;

    String executeScripts(Long urlMappingId, String httpRequestContent)  throws ScriptServiceException;
}

