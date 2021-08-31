package br.com.hmk.unittest.usecase.user;

import br.com.hmk.unittest.domain.User;

public interface FindUserByEmailUseCase {

    User process(String email);

}
