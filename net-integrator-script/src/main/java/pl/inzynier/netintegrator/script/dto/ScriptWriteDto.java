package pl.inzynier.netintegrator.script.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScriptWriteDto {
    Long urlMappingId;
    String content;
    ScriptType scriptType;
}

