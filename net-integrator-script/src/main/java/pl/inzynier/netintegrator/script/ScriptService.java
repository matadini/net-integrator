package pl.inzynier.netintegrator.script;

import pl.inzynier.netintegrator.script.dto.ScriptReadDto;
import pl.inzynier.netintegrator.script.dto.ScriptServiceException;
import pl.inzynier.netintegrator.script.dto.ScriptWriteDto;

import java.util.List;


public interface ScriptService {

    Long addScript(ScriptWriteDto dto) throws ScriptServiceException;

    List<ScriptReadDto> findByUrlMappingId(Long urlMappingId) throws ScriptServiceException;

    List<ScriptReadDto> findAll() throws ScriptServiceException;

    String executeScripts(Long urlMappingId, String httpRequestContent)  throws ScriptServiceException;
}

