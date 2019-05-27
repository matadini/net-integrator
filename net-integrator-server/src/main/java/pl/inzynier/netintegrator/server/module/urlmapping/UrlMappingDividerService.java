package pl.inzynier.netintegrator.server.module.urlmapping;

import com.google.common.collect.Maps;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Value
@Component
class UrlMappingDividerService {

    RestTemplate restTemplate;
    UrlMappingRepository mappingRepository;
    Map<RequestMethod, TargetMethodManager> requestMethodManagerStrategyMap;

    @Autowired
    public UrlMappingDividerService(RestTemplate restTemplate, UrlMappingRepository mappingRepository) {
        this.restTemplate = restTemplate;
        this.mappingRepository = mappingRepository;
        this.requestMethodManagerStrategyMap = this.initMap();
    }

    private Map<RequestMethod, TargetMethodManager> initMap() {
        Map<RequestMethod, TargetMethodManager> hashMap = Maps.newHashMap();
        hashMap.put(RequestMethod.GET, new TargetMethodManagerGet(restTemplate));
        hashMap.put(RequestMethod.POST, new TargetMethodManagerPost(restTemplate));
        return hashMap;
    }

    public ResponseEntity<String> manage(HttpServletRequest request, HttpServletResponse response) throws UrlMappingDividerServiceException {

        // Odczytaj danie zadania wejsciowego
        RequestMethod requestMethod = RequestMethod.valueOf(request.getMethod());
        String requestURI = request.getRequestURI();

        // odczytaj z bazy mapping
        Optional<UrlMapping> byPublishUrlAndPublishMethod = mappingRepository.findByPublishUrlAndPublishMethod(
                requestURI, requestMethod);
        if (!byPublishUrlAndPublishMethod.isPresent()) {
            throw new UrlMappingDividerServiceException("No config");
        }

        // okreslic typ metody http i wykonac zadanie na nowy adres
        UrlMapping urlMapping = byPublishUrlAndPublishMethod.get();
        TargetEndpoint target = urlMapping.getTarget();
        RequestMethod method = target.getMethod();
        TargetMethodManager targetMethodManager = requestMethodManagerStrategyMap.get(method);
        return targetMethodManager.manage(target, request);
    }
}
