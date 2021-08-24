package br.com.hkanno.unittest.port.repository;

import br.com.hkanno.unittest.domain.User;

import java.util.Optional;

public interface UserRepository {

    User save(User user);

    Optional<User> findByEmail(String email);

}
