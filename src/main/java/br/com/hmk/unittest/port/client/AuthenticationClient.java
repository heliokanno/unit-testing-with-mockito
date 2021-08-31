package br.com.hmk.unittest.port.client;

public interface AuthenticationClient {

    AuthDetail authenticate(String username, String password);
}
