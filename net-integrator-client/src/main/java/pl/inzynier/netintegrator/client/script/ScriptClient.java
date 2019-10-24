package pl.inzynier.netintegrator.client.script;

import pl.inzynier.netintegrator.client.script.dto.ScriptClientException;
import pl.inzynier.netintegrator.client.script.dto.ScriptReadDto;
import pl.inzynier.netintegrator.client.script.dto.ScriptWriteDto;

import java.util.List;

public interface ScriptClient {

    Long addScript(ScriptWriteDto dto) throws ScriptClientException;

    List<ScriptReadDto> findByUrlMappingId(Long urlMappingId) throws ScriptClientException;

    static ScriptClient create(String address) {
        return new ScriptClientStub();
    }
}

