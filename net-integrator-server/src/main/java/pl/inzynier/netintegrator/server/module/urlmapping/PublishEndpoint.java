package pl.inzynier.netintegrator.server.module.urlmapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
class PublishEndpoint {

    @Column(name = "publish_url", nullable = false, unique = true)
    String methodUrl;

    @Column(name ="publish_method", nullable = false)
    @Enumerated(EnumType.STRING)
    RequestMethod method;
}

