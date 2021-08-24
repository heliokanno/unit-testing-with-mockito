package br.com.hkanno.unittest.usecase.user;

import br.com.hkanno.unittest.domain.User;
import br.com.hkanno.unittest.exception.UserAlreadyExistsException;
import br.com.hkanno.unittest.port.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class CreateUserUseCaseImpl implements CreateUserUseCase {

    private final UserRepository userRepository;

    public User process(String email, String password) {

        var persistedUser = userRepository.findByEmail(email);
        if (persistedUser.isEmpty()) {
            throw new UserAlreadyExistsException();
        }

        var user = User.builder()
                .email(email)
                .password(password)
                .build();
        return userRepository.save(user);
    }
}
