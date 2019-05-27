package pl.inzynier.netintegrator.server.module.script;

import lombok.*;
import net.bytebuddy.asm.Advice;
import pl.inzynier.netintegrator.server.module.script.dto.ScriptType;

import javax.persistence.*;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "scripts")
class Script {

    @Id
    @Column(nullable = false, unique = true, name = "script_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long scriptId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    ScriptType type;

    @Column(nullable = false, length = Integer.MAX_VALUE)
    String content;

    @Column(nullable = false)
    Integer execOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "url_mapping_id")
    UrlMappingParent urlMappingParent;

}



