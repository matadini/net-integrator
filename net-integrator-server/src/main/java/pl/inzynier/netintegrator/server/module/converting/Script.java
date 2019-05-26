package pl.inzynier.netintegrator.server.module.converting;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "converters")
class Script {

    @Id
    @Column(nullable = false, unique = true, name = "script_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long converterId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    ScriptType scriptType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "url_mapping_id")
    UrlMappingParent urlMappingParent;

}
