package pl.inzynier.netintegrator.script;

import java.util.List;

import org.springframework.stereotype.Service;

import pl.inzynier.netintegrator.script.dto.ScriptDto;
import pl.inzynier.netintegrator.script.dto.ScriptServiceException;
import pl.inzynier.netintegrator.script.dto.ScriptType;

@Service
public interface ScriptService {

    Long addScript(Long urlMappingId, ScriptType type, String content) throws ScriptServiceException;

    List<ScriptDto> findByUrlMappingId(Long urlMappingId) throws ScriptServiceException;

    String executeScripts(Long urlMappingId, String httpRequestContent)  throws ScriptServiceException;
}
