package pl.inzynier.netintegrator.loadbalancer.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LoadBalancerIpOutputData {
    String addressIp;
}
