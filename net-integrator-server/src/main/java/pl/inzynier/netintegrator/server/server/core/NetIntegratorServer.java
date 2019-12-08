package pl.inzynier.netintegrator.server.server.core;

import pl.inzynier.netintegrator.mapping.core.dto.UrlMappingReadDto;

public interface NetIntegratorServer {
    void addRouting(UrlMappingReadDto mapping);
}
