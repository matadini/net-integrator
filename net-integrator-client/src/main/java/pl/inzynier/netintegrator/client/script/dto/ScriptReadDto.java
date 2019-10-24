package pl.inzynier.netintegrator.client.script.dto;

import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ScriptReadDto extends ScriptWriteDto {

    @NonNull
    Long scriptId;

    public ScriptReadDto(@NonNull Long urlMappingId, @NonNull String content, @NonNull ScriptType scriptType, @NonNull Long scriptId) {
        super(urlMappingId, content, scriptType);
        this.scriptId = scriptId;
    }
}
