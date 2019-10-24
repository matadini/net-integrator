package pl.inzynier.netintegrator.client.script.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScriptWriteDto {

    @NonNull
    Long urlMappingId;

    @NonNull
    String content;

    @NonNull
    ScriptType scriptType;
}

