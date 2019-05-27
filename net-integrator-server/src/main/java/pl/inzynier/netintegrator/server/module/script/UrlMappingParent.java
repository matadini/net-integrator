package pl.inzynier.netintegrator.server.module.script;

import com.google.common.collect.Sets;
import lombok.Data;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;
import java.util.Set;

@Data
@Entity
@Table(name = "url_mapping")
class UrlMappingParent {

    @Id
    @Column(nullable = false, unique = true, name = "url_mapping_id")
    Long urlMappingParentId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "urlMappingParent")
    Set<Script> converters = Sets.newHashSet();
}

