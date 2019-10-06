package pl.inzynier.netintegrator.loadbalancer;

import pl.inzynier.netintegrator.loadbalancer.dto.LoadBalancerIpOutputData;
import pl.inzynier.netintegrator.loadbalancer.dto.LoadBalancerServiceException;

class LoadBalancerServiceImpl implements LoadBalancerService {

    @Override
    public LoadBalancerIpOutputData getHostIp(Long urlMappingId) throws LoadBalancerServiceException {
        return LoadBalancerIpOutputData.builder().addressIp("localhost").build();
    }
}
