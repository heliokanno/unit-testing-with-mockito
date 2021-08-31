package br.com.hmk.unittest.usecase.user;

import br.com.hmk.unittest.domain.User;
import br.com.hmk.unittest.exception.UserAlreadyExistsException;
import br.com.hmk.unittest.port.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepository userRepository;

    public void process(String email, String password) {

        var persistedUser = userRepository.findByEmail(email);
        if (persistedUser.isPresent()) {
            throw new UserAlreadyExistsException();
        }

        var user = User.builder()
                .email(email)
                .password(password)
                .build();
        userRepository.save(user);
    }
}
