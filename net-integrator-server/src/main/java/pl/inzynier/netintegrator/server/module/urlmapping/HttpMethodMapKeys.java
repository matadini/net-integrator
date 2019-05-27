package pl.inzynier.netintegrator.server.module.urlmapping;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class HttpMethodMapKeys {

    public static final String CONNECTOR = "_TO_";
    public static final String GET_TO_GET = "GET" + CONNECTOR + "GET";
    public static final String POST_TO_POST = "POST" + CONNECTOR + "POST";
}
