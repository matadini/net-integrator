package pl.inzynier.netintegrator.server.module.urlmapping;

import com.google.common.annotations.Beta;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Value
@Component
class UrlMappingDividerService  {

    RestTemplate restTemplate;
    UrlMappingRepository mappingRepository;

    @Autowired
    public UrlMappingDividerService(RestTemplate restTemplate, UrlMappingRepository mappingRepository) {
        this.restTemplate = restTemplate;
        this.mappingRepository = mappingRepository;

   //     supportAll(restTemplate);
    }

//    private void supportAll(RestTemplate restTemplate) {
//        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
//        //Add the Jackson Message converter
//        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
//        // Note: here we are making this converter to process any kind of response,
//        // not only application/*json, which is the default behaviour
//        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
//        messageConverters.add(converter);
//        restTemplate.setMessageConverters(messageConverters);
//    }

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
        if (RequestMethod.GET.equals(method)) {

            // utworz nowy URI na podstawie target + parametrow get
            String fullUrl = target.getFullUrl();
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
        }

        return null;
    }
}
