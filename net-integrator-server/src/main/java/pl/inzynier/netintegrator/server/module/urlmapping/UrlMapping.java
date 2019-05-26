package pl.inzynier.netintegrator.server.module.urlmapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
class UrlMapping {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long urlMappingId;

    String endpoing;

    @Enumerated(EnumType.STRING)
    UrlMappingMethod method;

    String target;

}

