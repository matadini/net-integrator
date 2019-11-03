package pl.inzynier.netintegrator.mapping;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.inzynier.netintegrator.http.util.RequestMethod;

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

    @Column(name = "target_method")
    @Enumerated(EnumType.STRING)
    RequestMethod method;

    @Column(name = "target_host_address")
    String hostAddress;
}
