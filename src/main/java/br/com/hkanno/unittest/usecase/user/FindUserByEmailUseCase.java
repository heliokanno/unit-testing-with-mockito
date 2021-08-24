package br.com.hkanno.unittest.usecase.user;

import br.com.hkanno.unittest.domain.User;

public interface FindUserByEmailUseCase {

    User process(String email);

}
