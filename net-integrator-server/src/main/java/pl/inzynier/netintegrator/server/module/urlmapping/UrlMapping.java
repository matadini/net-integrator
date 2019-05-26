package pl.inzynier.netintegrator.server.module.urlmapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
class UrlMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long urlMappingId;

    @Embedded
    PublishEndpoint endpoint;

    @Embedded
    TargetEndpoint target;

}

