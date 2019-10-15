package pl.inzynier.netintegrator.client.login;

public interface LoginClient {

    boolean authorization(String login, String password);

    static LoginClient create(String address) {
        return new LoginClientStub();
    }
}

