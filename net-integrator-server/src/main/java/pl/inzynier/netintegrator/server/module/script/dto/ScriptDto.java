package pl.inzynier.netintegrator.server.module.script.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ScriptDto {
    ScriptType scriptType;
    String content;
}
