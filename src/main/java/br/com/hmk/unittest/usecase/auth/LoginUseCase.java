package br.com.hmk.unittest.usecase.auth;

import br.com.hmk.unittest.port.client.AuthDetail;

public interface LoginUseCase {

    AuthDetail process(String email, String password);

}
