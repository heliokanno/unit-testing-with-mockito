package br.com.hmk.unittest.usecase.user;

import br.com.hmk.unittest.domain.User;
import br.com.hmk.unittest.exception.UserNotFoundException;
import br.com.hmk.unittest.port.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class FindUserByEmailUseCaseImpl implements FindUserByEmailUseCase {

    private final UserRepository userRepository;

    public User process(String email) {
        return userRepository.findByEmail(email).orElseThrow(UserNotFoundException::new);
    }
}
