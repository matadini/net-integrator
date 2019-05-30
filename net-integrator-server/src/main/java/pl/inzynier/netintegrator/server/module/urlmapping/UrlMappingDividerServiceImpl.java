package pl.inzynier.netintegrator.server.module.urlmapping;

import com.google.common.collect.Maps;
import groovy.lang.GroovyShell;
import lombok.Value;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import pl.inzynier.netintegrator.loadbalancer.LoadBalancerService;
import pl.inzynier.netintegrator.server.module.script.ScriptService;
import pl.inzynier.netintegrator.server.module.urlmapping.dto.UrlMappingDto;
import pl.inzynier.netintegrator.server.module.urlmapping.manager.TargetMethodManagerException;
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

    ModelMapper modelMapper;
    TargetMethodManager getToGet;
    TargetMethodManager postToPost;
    UrlMappingRepository mappingRepository;
    Map<String, TargetMethodManager> requestMethodManagerStrategyMap;
    HttpMethodMapKeyGenerator httpMethodMapKeyGenerator;

    @Autowired
    public UrlMappingDividerServiceImpl(
            ModelMapper modelMapper,
            UrlMappingRepository mappingRepository,
            HttpMethodMapKeyGenerator httpMethodMapKeyGenerator,
            @Qualifier(HttpMethodMapKeys.GET_TO_GET) TargetMethodManager getToGet,
            @Qualifier(HttpMethodMapKeys.POST_TO_POST) TargetMethodManager postToPost) {

        this.mappingRepository = mappingRepository;
        this.httpMethodMapKeyGenerator = httpMethodMapKeyGenerator;
        this.modelMapper = modelMapper;
        this.getToGet = getToGet;
        this.postToPost = postToPost;
        this.requestMethodManagerStrategyMap = initMap();
    }

    private Map<String, TargetMethodManager> initMap() {
        Map<String, TargetMethodManager> hashMap = Maps.newHashMap();
        hashMap.put(HttpMethodMapKeys.GET_TO_GET, getToGet);
        hashMap.put(HttpMethodMapKeys.POST_TO_POST, postToPost);
        return hashMap;
    }

    @Override
    public ResponseEntity<String> manage(HttpServletRequest request, HttpServletResponse response) throws UrlMappingDividerServiceException {

        try {
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
        } catch (TargetMethodManagerException e) {
            throw new UrlMappingDividerServiceException(e);

        }
    }
}
