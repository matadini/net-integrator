package pl.inzynier.netintegrator.script;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import groovy.lang.GroovyShell;
import pl.inzynier.netintegrator.script.dto.ScriptDto;
import pl.inzynier.netintegrator.script.dto.ScriptServiceException;
import pl.inzynier.netintegrator.script.dto.ScriptType;

@Component
class ScriptServiceImpl implements ScriptService {

    private final GroovyShell groovyShell;
    private final ModelMapper modelMapper;
    private final ScriptRepository scriptRepository;


    @Autowired
    public ScriptServiceImpl(ScriptRepository scriptRepository, ModelMapper modelMapper, GroovyShell groovyShell) {
        this.scriptRepository = scriptRepository;
        this.modelMapper = modelMapper;
        this.groovyShell = groovyShell;
    }

    @Override
    public Long addScript(Long urlMappingId, ScriptType type, String content) throws ScriptServiceException {


        Script script = new Script(null, type, content, 0, urlMappingId);
        Script save = scriptRepository.save(script);
        return save.getScriptId();
    }

    @Override
    public List<ScriptDto> findByUrlMappingId(Long urlMappingId) throws ScriptServiceException {
        return scriptRepository.findByUrlMappingParentId(urlMappingId)
                .stream()
                .map(x -> modelMapper.map(x, ScriptDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public String executeScripts(Long urlMappingId, String httpRequestContent) throws ScriptServiceException {

        String body = "";
        List<ScriptDto> byUrlMappingId = this.findByUrlMappingId(urlMappingId);
        for (ScriptDto script : byUrlMappingId) {
            String content = script.getContent();
            Object skrypt = groovyShell.run(content, "skrypt", new String[]{httpRequestContent});
            body = skrypt.toString();
        }
        return body;
    }
}
