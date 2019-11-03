package pl.inzynier.netintegrator.script;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.inzynier.netintegrator.script.dto.ScriptType;

import javax.persistence.*;


@Data
@Entity
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "scripts")
class Script {

    @Id
    @Column(nullable = false, unique = true, name = "script_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long scriptId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    ScriptType type;

    @Column(nullable = false, length=10485760)
    String content;

    @Column(nullable = false)
    Integer execOrder;

    @Column(nullable = false)
    Long urlMappingId;

}



