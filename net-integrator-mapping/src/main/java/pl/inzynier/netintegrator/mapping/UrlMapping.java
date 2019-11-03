package pl.inzynier.netintegrator.mapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "url_mapping")
class UrlMapping {

    @Id
    @Column(nullable = false, unique = true, name = "url_mapping_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

