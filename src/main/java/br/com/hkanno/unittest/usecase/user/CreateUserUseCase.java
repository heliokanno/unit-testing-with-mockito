package br.com.hkanno.unittest.usecase.user;

import br.com.hkanno.unittest.domain.User;

public interface CreateUserUseCase {

    User process(String email, String password);

}
