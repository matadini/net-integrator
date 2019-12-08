package pl.inzynier.netintegrator.client.script;

import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.client.script.dto.ScriptClientException;
import pl.inzynier.netintegrator.client.script.dto.ScriptReadDto;
import pl.inzynier.netintegrator.client.script.dto.ScriptWriteDto;

import java.util.List;

public interface ScriptClient {

    Long create(ScriptWriteDto dto) throws ScriptClientException;

    void update(ScriptReadDto dto) throws ScriptClientException;

    List<ScriptReadDto> findByUrlMappingId(Long urlMappingId) throws ScriptClientException;

    void delete(ScriptReadDto dto) throws ScriptClientException;

    static ScriptClient create(String address) {
        return new ScriptClientStub();
    }

}



