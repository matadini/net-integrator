package pl.inzynier.netintegrator.script;

import java.util.List;


import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.inzynier.netintegrator.script.dto.ScriptReadDto;
import pl.inzynier.netintegrator.script.dto.ScriptServiceException;
import pl.inzynier.netintegrator.script.dto.ScriptType;
import pl.inzynier.netintegrator.script.dto.ScriptWriteDto;


public interface ScriptService {

    Long addScript(ScriptWriteDto dto) throws ScriptServiceException;

    List<ScriptReadDto> findByUrlMappingId(Long urlMappingId) throws ScriptServiceException;

    List<ScriptReadDto> findAll(Long urlMappingId) throws ScriptServiceException;

    String executeScripts(Long urlMappingId, String httpRequestContent)  throws ScriptServiceException;
}

