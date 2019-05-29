package pl.inzynier.netintegrator.server.module.loadbalancer;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "load_balancer_config_host")
class LoadBalancerConfigHost {

    @Id
    @Column(nullable = false, unique = true, name = "load_balancer_config_host_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long LoadBalancerConfigHostId;

    @Column(nullable = false)
    String serverAddress;

    @ManyToOne
    @JoinColumn(name = "load_balancer_config_id")
    LoadBalancerConfig loadBalancerConfig;

}
