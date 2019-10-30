package pl.inzynier.netintegrator.server.server.core;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class NetIntegratorContext {

    private NetIntegratorServer netIntegratorServer;

    public static NetIntegratorContext getContext() {
        return NetIntegratorServerHolder.INSTANCE;
    }


    private static class NetIntegratorServerHolder {
        static NetIntegratorContext INSTANCE = new NetIntegratorContext();
    }

}
