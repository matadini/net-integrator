package pl.inzynier.netintegrator.server.module.loadbalancer;

import com.google.common.collect.Lists;
import lombok.Data;
import lombok.ToString;
import org.hibernate.validator.constraints.SafeHtml;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "load_balancer_config")
class LoadBalancerConfig {

    @Id
    @Column(nullable = false, unique = true, name = "load_balancer_config_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long loadBalancerConfigId;

    @OneToMany(mappedBy = "loadBalancerConfig")
    List<LoadBalancerConfigHost> hosts = Lists.newArrayList();

}

