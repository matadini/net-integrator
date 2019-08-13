package pl.inzynier.netintegrator.server.module.urlmapping;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "url_mapping")
class UrlMapping {

    @Id
    @Column(nullable = false, unique = true, name = "url_mapping_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long urlMappingId;

    @Embedded
    PublishEndpoint endpoint;

    @Embedded
    TargetEndpoint target;

    public UrlMapping(PublishEndpoint endpoint, TargetEndpoint target) {
        this.endpoint = endpoint;
        this.target = target;
    }
}

