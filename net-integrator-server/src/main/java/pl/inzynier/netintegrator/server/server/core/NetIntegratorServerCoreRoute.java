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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

/**
 * Klasa obsluguje przychodzacy request, bez znaczenia na adres publish
 */
@RequiredArgsConstructor
public class NetIntegratorServerCoreRoute implements Route {

    private final UrlMappingService mappingService;
    private final HttpMethodMapKeyGenerator httpKeyGenerator;
    private final Map<String, TargetMethodManager> strategyMap;

    @Override
    public Object handle(Request request, Response resp) throws Exception {

        // Okresl metode HTTP dla adresu do nasluchu
        String reqMethod = request.requestMethod();
        RequestMethod method = RequestMethod.valueOf(reqMethod);
        String requestURI = request.uri();

        // znajdz konfiguracje mapowania w bazie danych
        Optional<UrlMappingReadDto> toPublish =
                mappingService.findByPublishUrlAndPublishMethod(
                        requestURI, method);

        if (!toPublish.isPresent()) {
            return "Nie znaleziono konfiguracji";
        }

        // okresl strategie postepowania dla konfiguracji z bazy
        UrlMappingReadDto urlMapping = toPublish.get();
        String strategyKey = httpKeyGenerator.genreate(urlMapping);

        TargetMethodManager manager = strategyMap.get(strategyKey);
        HttpServletRequest reqRaw = request.raw();
        HttpServletResponse respRaw = resp.raw();
        return manager.manage(urlMapping, reqRaw, respRaw);
    }
}
