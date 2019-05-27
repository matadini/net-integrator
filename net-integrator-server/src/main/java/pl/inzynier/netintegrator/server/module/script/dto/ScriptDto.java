package pl.inzynier.netintegrator.server.module.script.dto;

import lombok.*;
import net.bytebuddy.asm.Advice;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScriptDto {
    ScriptType scriptType;
    String content;
}
