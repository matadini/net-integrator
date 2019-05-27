package pl.inzynier.netintegrator.server.module.script;

import pl.inzynier.netintegrator.server.module.script.dto.ScriptDto;
import pl.inzynier.netintegrator.server.module.script.dto.ScriptServiceException;
import pl.inzynier.netintegrator.server.module.script.dto.ScriptType;

import java.util.List;

public interface ScriptService {

    Long addScript(Long urlMappingId, ScriptType type, String content) throws ScriptServiceException;

    List<ScriptDto> findByUrlMappingId(Long urlMappingId) throws ScriptServiceException;
}
