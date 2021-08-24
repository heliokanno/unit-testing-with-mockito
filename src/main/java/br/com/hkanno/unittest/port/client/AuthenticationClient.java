package br.com.hkanno.unittest.port.client;

import br.com.hkanno.unittest.domain.auth.UserDetail;

public interface AuthenticationClient {

    UserDetail login(String username, String password);
}
