package br.com.hkanno.unittest.usecase.auth;

import br.com.hkanno.unittest.domain.auth.UserDetail;

public interface LoginUseCase {

    UserDetail process(String email, String password);

}
