package pl.inzynier.netintegrator.server.server;

import lombok.Data;

/**
 * Jak wywali sie cos wewnatrz aplikacji (Internal error)
 * to zamiast bledu dzikiego zwracany jest json z odpowiednim statusem
 */
@Data
public class NetIntegratorAppResponse {
    String message;
}
