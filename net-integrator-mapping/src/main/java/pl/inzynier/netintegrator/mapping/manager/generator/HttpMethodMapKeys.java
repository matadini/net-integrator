package pl.inzynier.netintegrator.mapping.manager.generator;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class HttpMethodMapKeys {

    public static final String CONNECTOR = "_TO_";
    public static final String GET_TO_GET = "GET" + CONNECTOR + "GET";
    public static final String POST_TO_POST = "POST" + CONNECTOR + "POST";
}
