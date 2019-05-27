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
class TargetEndpoint {

    @Column(name = "target_url")
    String methodUrl;

    @Column(name = "target_server")
    String serverAddress;

    @Column(name = "target_method")
    @Enumerated(EnumType.STRING)
    RequestMethod method;

    String getFullUrl() {
        return this.getServerAddress() + this.getMethodUrl();
    }
}
