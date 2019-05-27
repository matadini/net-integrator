package pl.inzynier.netintegrator.server.module.script.dto;

import lombok.*;
import net.bytebuddy.asm.Advice;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScriptDto {
    String content;
    Integer execOrder;
    ScriptType scriptType;
}
