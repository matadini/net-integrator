package pl.inzynier.netintegrator.server.module.script;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.inzynier.netintegrator.server.module.script.dto.ScriptType;

import javax.persistence.*;


@Data
@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    Long urlMappingParentId;

}



