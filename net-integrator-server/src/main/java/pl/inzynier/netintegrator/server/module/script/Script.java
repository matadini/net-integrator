package pl.inzynier.netintegrator.server.module.script;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Value;
import net.bytebuddy.asm.Advice;
import pl.inzynier.netintegrator.server.module.script.dto.ScriptType;

import javax.persistence.*;


@Data
@Entity
@AllArgsConstructor
@Table(name = "scripts")
class Script {

    @Id
    @Column(nullable = false, unique = true, name = "script_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long scriptId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    ScriptType type;

    String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "url_mapping_id")
    UrlMappingParent urlMappingParent;

}



