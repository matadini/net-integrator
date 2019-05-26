package pl.inzynier.netintegrator.server.module.urlmapping;

import lombok.Builder;
import lombok.Value;

/**
 * Na wypadek personalizowanych zwrotek np. brak mappingu w konfiguracji
 */
@Value
@Builder
class UrlMappingResponse {
    String message;
}
