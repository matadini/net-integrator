package pl.inzynier.netintegrator.server.module.urlmapping;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.springframework.web.bind.annotation.RequestMethod;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
class TargetEndpoint {

    @Column(name = "target_url")
    String methodUrl;

    @Column(name = "target_method")
    @Enumerated(EnumType.STRING)
    RequestMethod method;
}
