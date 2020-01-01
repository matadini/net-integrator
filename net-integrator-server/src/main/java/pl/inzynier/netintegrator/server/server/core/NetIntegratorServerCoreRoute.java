package pl.inzynier.netintegrator.server.server.core;

import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.http.util.RequestMethod;
import pl.inzynier.netintegrator.mapping.core.UrlMappingService;
import pl.inzynier.netintegrator.mapping.core.dto.UrlMappingReadDto;
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

        // Okresl metode HTTP dla adresu do nasluchu
        RequestMethod requestMethod = RequestMethod.valueOf(request.requestMethod());
        String requestURI = request.uri();

        // znajdz konfiguracje mapowania w bazie danych
        Optional<UrlMappingReadDto> byPublishUrlAndPublishMethod =
                urlMappingService.findByPublishUrlAndPublishMethod(requestURI, requestMethod);
        if (!byPublishUrlAndPublishMethod.isPresent()) {
            return "Nie znaleziono konfiguracji";
        }

        // okresl strategie postepowania dla konfiguracji z bazy
        UrlMappingReadDto urlMapping = byPublishUrlAndPublishMethod.get();
        String strategyKey = httpMethodMapKeyGenerator.genreate(urlMapping);

        TargetMethodManager targetMethodManager = requestMethodManagerStrategyMap.get(strategyKey);
        return targetMethodManager.manage(urlMapping, request.raw(), resp.raw());
    }
}
