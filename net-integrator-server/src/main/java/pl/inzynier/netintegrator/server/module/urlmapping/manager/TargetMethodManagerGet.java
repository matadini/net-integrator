package pl.inzynier.netintegrator.server.module.urlmapping.manager;

import lombok.Builder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pl.inzynier.netintegrator.loadbalancer.LoadBalancerService;
import pl.inzynier.netintegrator.loadbalancer.dto.LoadBalancerIpInputData;
import pl.inzynier.netintegrator.loadbalancer.dto.LoadBalancerIpOutputData;
import pl.inzynier.netintegrator.loadbalancer.dto.LoadBalancerServiceException;
import pl.inzynier.netintegrator.server.module.urlmapping.dto.TargetEndpointDto;
import pl.inzynier.netintegrator.server.module.urlmapping.dto.UrlMappingDto;
import pl.inzynier.netintegrator.server.shared.HttpServletRequestUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Builder
class TargetMethodManagerGet implements TargetMethodManager {

    RestTemplate restTemplate;
    LoadBalancerService loadBalancerService;

    @Override
    public ResponseEntity<String> manage(UrlMappingDto urlMapping, HttpServletRequest request) {

        try {
            // utworz nowy URI na podstawie target + parametrow get
            Map<String, String> headerAsMap = HttpServletRequestUtil.getHeaderAsMap(request);
            LoadBalancerIpInputData build = LoadBalancerIpInputData.builder()
                    .requestHeader(headerAsMap)
                    .urlMappingId(urlMapping.getUrlMappingId())
                    .build();
            LoadBalancerIpOutputData ip = loadBalancerService.getHostIp(build);

            TargetEndpointDto target = urlMapping.getTarget();
            String fullUrl = target.getFullUrl(ip.getAddressIp());

            Map<String, String[]> parameterMap1 = request.getParameterMap();

            // przepisz zapytanie na nowy URL
            UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(fullUrl);
            for (String key : parameterMap1.keySet()) {
                for (String value : parameterMap1.get(key)) {
                    uriComponentsBuilder.queryParam(key, value);
                }
            }
            String s = uriComponentsBuilder.toUriString();

            // wyslij zadanie
            return restTemplate.getForEntity(s, String.class);

        } catch (LoadBalancerServiceException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
