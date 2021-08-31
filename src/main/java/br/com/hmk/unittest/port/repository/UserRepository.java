package br.com.hmk.unittest.port.repository;

import br.com.hmk.unittest.domain.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findByEmail(String email);

}
