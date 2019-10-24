package pl.inzynier.netintegrator.client.script;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import pl.inzynier.netintegrator.client.script.dto.ScriptClientException;
import pl.inzynier.netintegrator.client.script.dto.ScriptReadDto;
import pl.inzynier.netintegrator.client.script.dto.ScriptType;
import pl.inzynier.netintegrator.client.script.dto.ScriptWriteDto;

import java.util.List;
import java.util.Map;

class ScriptClientStub implements ScriptClient {

    private Map<Long, List<ScriptReadDto>> create() {
        Map<Long, List<ScriptReadDto>> map = Maps.newHashMap();

        // example 1
        map.put(2L, Lists.newArrayList(
                new ScriptReadDto(
                        2L,
                        ExampleGroovyScript.createExampleGroovyScriptJsonToXml(),
                        ScriptType.POST_CALL,
                        1L)));


        // example 3
        map.put(3L, Lists.newArrayList(
                new ScriptReadDto(
                        3L,
                        ExampleGroovyScript.createExampleGroovyScriptToUpperCase(),
                        ScriptType.POST_CALL,
                        2L)));

        return map;
    }

    private final Map<Long, List<ScriptReadDto>> database = create();

    @Override
    public Long addScript(ScriptWriteDto dto) throws ScriptClientException {
        return null;
    }

    @Override
    public List<ScriptReadDto> findByUrlMappingId(Long urlMappingId) throws ScriptClientException {
        if (database.containsKey(urlMappingId)) {
            return database.get(urlMappingId);
        }
        return Lists.newArrayList();
    }


}
