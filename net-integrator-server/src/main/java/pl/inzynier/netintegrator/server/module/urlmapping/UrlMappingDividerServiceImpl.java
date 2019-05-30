package pl.inzynier.netintegrator.server.module.urlmapping;

import com.google.common.collect.Maps;
import groovy.lang.GroovyShell;
import lombok.Value;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import pl.inzynier.netintegrator.loadbalancer.LoadBalancerService;
import pl.inzynier.netintegrator.server.module.script.ScriptService;
import pl.inzynier.netintegrator.server.module.urlmapping.dto.UrlMappingDto;
import pl.inzynier.netintegrator.server.module.urlmapping.manager.generator.HttpMethodMapKeyGenerator;
import pl.inzynier.netintegrator.server.module.urlmapping.manager.generator.HttpMethodMapKeys;
import pl.inzynier.netintegrator.server.module.urlmapping.manager.TargetMethodManager;
import pl.inzynier.netintegrator.server.module.urlmapping.manager.TargetMethodManagerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@Value
@Component
class UrlMappingDividerServiceImpl implements UrlMappingDividerService {

    RestTemplate restTemplate;
    ScriptService scriptService;
    GroovyShell groovyShell;
    UrlMappingRepository mappingRepository;
    Map<String, TargetMethodManager> requestMethodManagerStrategyMap;
    HttpMethodMapKeyGenerator httpMethodMapKeyGenerator;
    ModelMapper modelMapper;
    LoadBalancerService loadBalancerService;

    @Autowired
    public UrlMappingDividerServiceImpl(
            RestTemplate restTemplate,
            UrlMappingRepository mappingRepository,
            ScriptService scriptService,
            GroovyShell groovyShell,
            HttpMethodMapKeyGenerator httpMethodMapKeyGenerator,
            ModelMapper modelMapper,
            LoadBalancerService loadBalancerService) {

        this.restTemplate = restTemplate;
        this.mappingRepository = mappingRepository;
        this.scriptService = scriptService;
        this.groovyShell = groovyShell;
        this.httpMethodMapKeyGenerator = httpMethodMapKeyGenerator;
        this.modelMapper = modelMapper;
        this.loadBalancerService = loadBalancerService;
        this.requestMethodManagerStrategyMap = initMap();
    }

    // todo: te targetMethodManagery do jakis beanow i wrzucac przez konstruktor
    private Map<String, TargetMethodManager> initMap() {
        Map<String, TargetMethodManager> hashMap = Maps.newHashMap();
        hashMap.put(HttpMethodMapKeys.GET_TO_GET, TargetMethodManagerFactory.getToGet(restTemplate, loadBalancerService));
        hashMap.put(HttpMethodMapKeys.POST_TO_POST, TargetMethodManagerFactory.postToPost(restTemplate, scriptService, groovyShell, loadBalancerService));
        return hashMap;
    }

    @Override
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
        UrlMappingDto mappingDto = modelMapper.map(urlMapping, UrlMappingDto.class);
        String strategyKey = httpMethodMapKeyGenerator.genreate(mappingDto);

        // w zaleznosci od pary publish-target, strategia wywolania moze sie roznic
        TargetMethodManager targetMethodManager = requestMethodManagerStrategyMap.get(strategyKey);
        return targetMethodManager.manage(mappingDto, request);
    }
}
