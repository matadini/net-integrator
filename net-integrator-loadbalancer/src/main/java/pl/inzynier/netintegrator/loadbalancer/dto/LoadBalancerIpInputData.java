package pl.inzynier.netintegrator.loadbalancer.dto;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

@Value
@Builder
@ToString
public class LoadBalancerIpInputData {
    Long urlMappingId;
}


