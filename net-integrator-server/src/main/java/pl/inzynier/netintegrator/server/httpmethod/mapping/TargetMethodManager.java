package pl.inzynier.netintegrator.server.httpmethod.mapping;

import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import groovy.lang.GroovyShell;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import pl.inzynier.netintegrator.loadbalancer.LoadBalancerService;
import pl.inzynier.netintegrator.loadbalancer.dto.LoadBalancerIpOutputData;
import pl.inzynier.netintegrator.mapping.dto.TargetEndpointDto;
import pl.inzynier.netintegrator.mapping.dto.UrlMappingReadDto;
import pl.inzynier.netintegrator.script.ScriptService;
import pl.inzynier.netintegrator.server.httpmethod.generator.HttpMethodMapKeys;
import spark.Response;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Interfejs dla strategii mapowania requestow
 * - GET TO POST
 * - GET TO GET itp.
 */
@FunctionalInterface
public interface TargetMethodManager {

    Object manage(UrlMappingReadDto urlMapping, HttpServletRequest request, HttpServletResponse response) throws TargetMethodManagerException;

}


