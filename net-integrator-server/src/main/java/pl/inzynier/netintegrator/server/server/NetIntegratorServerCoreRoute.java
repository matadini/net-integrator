package pl.inzynier.netintegrator.server.server;

import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.http.util.RequestMethod;
import pl.inzynier.netintegrator.mapping.UrlMappingService;
import pl.inzynier.netintegrator.mapping.dto.UrlMappingReadDto;
import pl.inzynier.netintegrator.server.httpmethod.generator.HttpMethodMapKeyGenerator;
import pl.inzynier.netintegrator.server.httpmethod.mapping.TargetMethodManager;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.Map;
import java.util.Optional;

/**
 * Klasa obsluguje przychodzacy request, bez znaczenia na adres publish
 */
@RequiredArgsConstructor
public class NetIntegratorServerCoreRoute implements Route {

    private final UrlMappingService urlMappingService;
    private final HttpMethodMapKeyGenerator httpMethodMapKeyGenerator;
    private final Map<String, TargetMethodManager> requestMethodManagerStrategyMap;

    @Override
    public Object handle(Request request, Response resp) throws Exception {

        // Odczytaj danie zadania wejsciowego
        RequestMethod requestMethod = RequestMethod.valueOf(request.requestMethod());
        String requestURI = request.uri();

        // znajdz mapowanie w bazie
        Optional<UrlMappingReadDto> byPublishUrlAndPublishMethod = urlMappingService.findByPublishUrlAndPublishMethod(requestURI, requestMethod);
        if (!byPublishUrlAndPublishMethod.isPresent()) {
            return "Lipa jak sto pindzisiont";
        }

        // okres rodzaj mapowania
        UrlMappingReadDto urlMapping = byPublishUrlAndPublishMethod.get();
        String strategyKey = httpMethodMapKeyGenerator.genreate(urlMapping);

        TargetMethodManager targetMethodManager = requestMethodManagerStrategyMap.get(strategyKey);
        return targetMethodManager.manage(urlMapping, request.raw(), resp.raw());
    }
}
