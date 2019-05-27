package pl.inzynier.netintegrator.server.module.urlmapping;

import com.google.common.collect.Maps;
import lombok.Value;
import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import pl.inzynier.netintegrator.server.module.script.ScriptService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

@Value
@Component
class UrlMappingDividerServiceImpl implements UrlMappingDividerService {

    RestTemplate restTemplate;
    ScriptService scriptService;
    PythonInterpreter pythonInterpreter;
    UrlMappingRepository mappingRepository;
    Map<String, TargetMethodManager> requestMethodManagerStrategyMap;
    HttpMethodMapKeyGenerator httpMethodMapKeyGenerator;

    @Autowired
    public UrlMappingDividerServiceImpl(
            RestTemplate restTemplate,
            UrlMappingRepository mappingRepository,
            ScriptService scriptService,
            PythonInterpreter pythonInterpreter,
            HttpMethodMapKeyGenerator httpMethodMapKeyGenerator) {

        this.restTemplate = restTemplate;
        this.mappingRepository = mappingRepository;
        this.scriptService = scriptService;
        this.pythonInterpreter = pythonInterpreter;
        this.requestMethodManagerStrategyMap = initMap();
        this.httpMethodMapKeyGenerator = httpMethodMapKeyGenerator;
    }

    private Map<String, TargetMethodManager> initMap() {
        Map<String, TargetMethodManager> hashMap = Maps.newHashMap();
        hashMap.put(HttpMethodMapKeys.GET_TO_GET, new TargetMethodManagerGet(restTemplate));
        hashMap.put(HttpMethodMapKeys.POST_TO_POST, new TargetMethodManagerPost(restTemplate, scriptService, pythonInterpreter));
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
        String strategyKey = httpMethodMapKeyGenerator.genreate(urlMapping);

        // w zaleznosci od pary publish-target, strategia wywolania moze sie roznic
        TargetMethodManager targetMethodManager = requestMethodManagerStrategyMap.get(strategyKey);
        return targetMethodManager.manage(urlMapping, request);
    }
}
