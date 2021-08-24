package br.com.hkanno.unittest.usecase.user;

import br.com.hkanno.unittest.domain.User;
import br.com.hkanno.unittest.exception.UserNotFoundException;

import br.com.hkanno.unittest.port.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class FindUserByEmailUseCaseImpl implements FindUserByEmailUseCase {

    private final UserRepository userRepository;

    public User process(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }
}
