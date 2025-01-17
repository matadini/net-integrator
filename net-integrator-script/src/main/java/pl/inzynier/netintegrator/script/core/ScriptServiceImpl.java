package pl.inzynier.netintegrator.script.core;

import groovy.lang.GroovyShell;
import org.modelmapper.ModelMapper;
import pl.inzynier.netintegrator.script.core.dto.ScriptReadDto;
import pl.inzynier.netintegrator.script.core.dto.ScriptServiceException;
import pl.inzynier.netintegrator.script.core.dto.ScriptType;
import pl.inzynier.netintegrator.script.core.dto.ScriptWriteDto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


class ScriptServiceImpl implements ScriptService {

    private final GroovyShell groovyShell;
    private final ModelMapper modelMapper;
    private final ScriptRepository scriptRepository;

    ScriptServiceImpl(ScriptRepository scriptRepository, ModelMapper modelMapper, GroovyShell groovyShell) {
        this.scriptRepository = scriptRepository;
        this.modelMapper = modelMapper;
        this.groovyShell = groovyShell;
    }

    @Override
    public Long create(ScriptWriteDto dto) throws ScriptServiceException {


        ScriptType scriptType = dto.getScriptType();
        String content = dto.getContent();
        Long urlMappingId = dto.getUrlMappingId();

        Script script = new Script(null, scriptType, content, 0, urlMappingId);
        Script save = scriptRepository.save(script);
        return save.getScriptId();
    }

    @Override
    public List<ScriptReadDto> findByUrlMappingId(Long urlMappingId) throws ScriptServiceException {
        return scriptRepository.findByUrlMappingId(urlMappingId)
                .stream()
                .map(x -> modelMapper.map(x, ScriptReadDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long scriptId) throws ScriptServiceException {
        Optional<Script> byId = scriptRepository.findById(scriptId);
        byId.ifPresent(scriptRepository::delete);
    }

    @Override
    public String executeScripts(
            Long urlMappingId,
            String httpRequestContent,
            ScriptType scriptType) throws ScriptServiceException {

        // pobierz z bazy skrypty
        String body = httpRequestContent;
        List<ScriptReadDto> dtos = findByUrlMappingId(urlMappingId)
                .stream()
                .filter(x -> x.getScriptType().equals(scriptType))
                .collect(Collectors.toList());

        // zmodyfikuj dane wejsciowe skryptami
        for (ScriptReadDto script : dtos) {
            String content = script.getContent();
            Object skrypt = groovyShell.run(
                    content,
                    "skrypt",
                    new String[]{httpRequestContent});
            body = skrypt.toString();
        }
        return body;
    }
}
