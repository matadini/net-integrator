package pl.inzynier.netintegrator.server.module.loadbalancer;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "load_balancer_config_host")
class LoadBalancerConfigHost {

    @Id
    @Column(nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long loadBalancerConfigId;

    @Column(nullable = false)
    String serverAddress;

    @ManyToMany(mappedBy = "hosts")
    LoadBalancerConfig loadBalancerConfig;

}
