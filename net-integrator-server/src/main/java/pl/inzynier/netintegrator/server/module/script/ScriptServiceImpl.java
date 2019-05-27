package pl.inzynier.netintegrator.server.module.script;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.inzynier.netintegrator.server.module.script.dto.ScriptDto;
import pl.inzynier.netintegrator.server.module.script.dto.ScriptServiceException;
import pl.inzynier.netintegrator.server.module.script.dto.ScriptType;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
class ScriptServiceImpl implements ScriptService {

    private final ScriptRepository scriptRepository;

    private final UrlMappingParentRepository mappingParentRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public ScriptServiceImpl(ScriptRepository scriptRepository, UrlMappingParentRepository mappingParentRepository, ModelMapper modelMapper) {
        this.scriptRepository = scriptRepository;
        this.mappingParentRepository = mappingParentRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Long addScript(Long urlMappingId, ScriptType type, String content) throws ScriptServiceException {

        Optional<UrlMappingParent> mappingParent = mappingParentRepository.findById(urlMappingId);
        if (!mappingParent.isPresent()) {
            throw new ScriptServiceException("lipne urlMappingId");
        }
        UrlMappingParent urlMappingParent = mappingParent.get();

        Script script = new Script(null, type, content, 0, urlMappingParent);
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
}
