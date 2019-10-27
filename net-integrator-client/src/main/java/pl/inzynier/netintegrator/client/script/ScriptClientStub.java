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
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

class ScriptClientStub implements ScriptClient {

    private final AtomicLong sequence = new AtomicLong(0);

    private final Map<Long, List<ScriptReadDto>> database = Maps.newHashMap();

    ScriptClientStub() {
        try {
            this.create(
                    new ScriptReadDto(
                            2L,
                            ExampleGroovyScript.createExampleGroovyScriptJsonToXml(),
                            ScriptType.POST_CALL,
                            sequence.getAndIncrement()));
            this.create(
                    new ScriptReadDto(
                            3L,
                            ExampleGroovyScript.createExampleGroovyScriptToUpperCase(),
                            ScriptType.POST_CALL,
                            sequence.getAndIncrement()));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public Long create(ScriptWriteDto dto) throws ScriptClientException {
        @NonNull Long urlMappingId = dto.getUrlMappingId();
        if (!database.containsKey(urlMappingId)) {
            database.put(urlMappingId, Lists.newArrayList());
        }
        long andIncrement = sequence.get();
        List<ScriptReadDto> scriptReadDtos = database.get(urlMappingId);
        scriptReadDtos.add(new ScriptReadDto(
                urlMappingId,
                ExampleGroovyScript.createExampleGroovyScriptJsonToXml(),
                ScriptType.POST_CALL,
                andIncrement));
        return andIncrement;
    }

    @Override
    public void update(ScriptReadDto dto) throws ScriptClientException {
        if (Objects.isNull(dto.getScriptId())) {

        } else {
            @NonNull Long urlMappingId = dto.getUrlMappingId();
            if (database.containsKey(urlMappingId)) {
                List<ScriptReadDto> scriptReadDtos = database.get(urlMappingId);
                Optional<ScriptReadDto> first = scriptReadDtos.stream()
                        .filter(x -> x.getScriptId().equals(dto.getScriptId()))
                        .findFirst();
                if (first.isPresent()) {
                    remove(urlMappingId, first.get().getScriptId());
                    scriptReadDtos.add(dto);
                }
            }
        }
    }

    @Override
    public List<ScriptReadDto> findByUrlMappingId(Long urlMappingId) throws ScriptClientException {
        if (database.containsKey(urlMappingId)) {
            return database.get(urlMappingId);
        }
        return Lists.newArrayList();
    }

    @Override
    public void delete(ScriptReadDto dto) throws ScriptClientException {
        @NonNull Long urlMappingId = dto.getUrlMappingId();
        if (database.containsKey(urlMappingId)) {
            @NonNull Long scriptId = dto.getScriptId();
            remove(urlMappingId, scriptId);
        }
    }

    private void remove(@NonNull Long urlMappingId, @NonNull Long scriptId) {
        List<ScriptReadDto> scriptReadDtos = database.get(urlMappingId);
        Optional<ScriptReadDto> first = scriptReadDtos.stream()
                .filter(x -> x.getScriptId().equals(scriptId))
                .findFirst();
        first.ifPresent(scriptReadDtos::remove);
    }


}
