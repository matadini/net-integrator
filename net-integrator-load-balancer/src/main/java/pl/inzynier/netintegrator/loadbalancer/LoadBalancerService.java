package pl.inzynier.netintegrator.loadbalancer;


import pl.inzynier.netintegrator.loadbalancer.dto.LoadBalancerIpInputData;
import pl.inzynier.netintegrator.loadbalancer.dto.LoadBalancerIpOutputData;
import pl.inzynier.netintegrator.loadbalancer.dto.LoadBalancerServiceException;

public interface LoadBalancerService {
    LoadBalancerIpOutputData getHostIp(LoadBalancerIpInputData data) throws LoadBalancerServiceException;
}
