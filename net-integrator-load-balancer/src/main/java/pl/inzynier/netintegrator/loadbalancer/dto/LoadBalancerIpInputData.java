package pl.inzynier.netintegrator.loadbalancer.dto;

import lombok.Builder;
import lombok.Value;

import java.util.Map;

@Value
@Builder
public class LoadBalancerIpInputData {
    Long urlMappingId;
    Map<String, String> requestHeader;
}
