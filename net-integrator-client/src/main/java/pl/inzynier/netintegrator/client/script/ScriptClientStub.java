package pl.inzynier.netintegrator.client.script;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.NonNull;
import pl.inzynier.netintegrator.client.script.dto.ScriptClientException;
import pl.inzynier.netintegrator.client.script.dto.ScriptReadDto;
import pl.inzynier.netintegrator.client.script.dto.ScriptType;
import pl.inzynier.netintegrator.client.script.dto.ScriptWriteDto;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

class ScriptClientStub implements ScriptClient {

    private final AtomicLong sequence = new AtomicLong(0);


    private final Map<Long, List<ScriptReadDto>> database = Maps.newHashMap();

    ScriptClientStub() {
        try {
            this.addScript(
                    new ScriptReadDto(
                            2L,
                            ExampleGroovyScript.createExampleGroovyScriptJsonToXml(),
                            ScriptType.POST_CALL,
                            sequence.getAndIncrement()));
            this.addScript(
                    new ScriptReadDto(
                            3L,
                            ExampleGroovyScript.createExampleGroovyScriptToUpperCase(),
                            ScriptType.POST_CALL,
                            sequence.getAndIncrement()));
        } catch (Exception ex) {

        }
    }

    @Override
    public Long addScript(ScriptWriteDto dto) throws ScriptClientException {
        @NonNull Long urlMappingId = dto.getUrlMappingId();
        if (!database.containsKey(urlMappingId)) {
            database.put(urlMappingId, Lists.newArrayList());
        }
        long andIncrement = sequence.getAndIncrement();
        List<ScriptReadDto> scriptReadDtos = database.get(urlMappingId);
        scriptReadDtos.add(new ScriptReadDto(
                urlMappingId,
                ExampleGroovyScript.createExampleGroovyScriptJsonToXml(),
                ScriptType.POST_CALL,
                andIncrement));
        return andIncrement;
    }

    @Override
    public List<ScriptReadDto> findByUrlMappingId(Long urlMappingId) throws ScriptClientException {
        if (database.containsKey(urlMappingId)) {
            return database.get(urlMappingId);
        }
        return Lists.newArrayList();
    }


}
