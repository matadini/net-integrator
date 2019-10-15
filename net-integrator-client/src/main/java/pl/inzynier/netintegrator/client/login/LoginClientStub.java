package pl.inzynier.netintegrator.client.login;

class LoginClientStub implements LoginClient {

    @Override
    public boolean authorization(String login, String password) {

        return "admin".equals(login) && "admin".equals(password);
    }
}
