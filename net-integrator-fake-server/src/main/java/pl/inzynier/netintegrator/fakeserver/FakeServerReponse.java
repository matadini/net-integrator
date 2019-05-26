package pl.inzynier.netintegrator.fakeserver;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
class FakeServerReponse {

    int statusCode;
    String message;
    Object object;

}
