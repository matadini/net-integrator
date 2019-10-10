package pl.inzynier.netintegrator.server.httpmethod.mapping;

import lombok.Data;

/**
 * Jak wywali sie cos wewnatrz aplikacji (Internal error)
 * to zamiast bledu dzikiego zwracany jest json z odpowiednim statusem
 */
@Data
class NetIntegratorAppResponse {
    String message;
}
