package pl.inzynier.netintegrator.loadbalancer.dto;

import lombok.Builder;
import lombok.ToString;
import lombok.Value;

import java.util.Map;

@Value
@Builder
@ToString
public class LoadBalancerIpInputData {
    Long urlMappingId;
    Map<String, String> requestHeader;
}


