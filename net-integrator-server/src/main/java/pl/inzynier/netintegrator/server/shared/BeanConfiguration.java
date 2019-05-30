package pl.inzynier.netintegrator.server.shared;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import groovy.lang.GroovyShell;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import pl.inzynier.netintegrator.loadbalancer.LoadBalancerService;
import pl.inzynier.netintegrator.loadbalancer.dto.LoadBalancerIpInputData;
import pl.inzynier.netintegrator.loadbalancer.dto.LoadBalancerIpOutputData;
import pl.inzynier.netintegrator.loadbalancer.dto.LoadBalancerServiceException;

@Configuration
class BeanConfiguration {

    @Bean
    Gson gson() {
        return new GsonBuilder().serializeNulls().create();
    }

    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    GroovyShell groovyShell() {
        return new  GroovyShell();
    }

    @Bean
    LoadBalancerService loadBalancerService() {
        return new LoadBalancerService() {

            @Override
            public LoadBalancerIpOutputData getHostIp(LoadBalancerIpInputData data) throws LoadBalancerServiceException {

                System.out.println(data);

                return LoadBalancerIpOutputData.builder()
                        .addressIp("http://localhost:9090")
                        .build();
            }
        };
    }
}
