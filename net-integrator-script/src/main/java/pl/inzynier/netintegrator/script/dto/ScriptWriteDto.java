package pl.inzynier.netintegrator.script.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScriptWriteDto {
    Long urlMappingId;
    String content;
    ScriptType scriptType;
}

