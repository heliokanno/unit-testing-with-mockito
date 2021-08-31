package br.com.hmk.unittest.usecase.user;

import br.com.hmk.unittest.domain.User;
import br.com.hmk.unittest.exception.UserAlreadyExistsException;
import br.com.hmk.unittest.port.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class CreateUserUseCaseTest {

    @InjectMocks
    CreateUserUseCaseImpl createUserUseCase;

    @Mock
    UserRepository userRepository;

    @Test
    void shouldPersistAndReturnUserOnSuccess() {
        var userCaptor = ArgumentCaptor.forClass(User.class);
        var email = "test@mock.com";
        var password = "pws12345";

        createUserUseCase.process(email, password);

        verify(userRepository, times(1)).findByEmail(email);
        verify(userRepository, times(1)).save(userCaptor.capture());
        verifyNoMoreInteractions(userRepository);

        assertThat(userCaptor.getValue().getEmail()).isEqualTo(email);
        assertThat(userCaptor.getValue().getPassword()).isEqualTo(password);
    }

    @Test
    void shouldThrowUserAlreadyExistsExceptionWhenEmailInUse() {
        var email = "test@mock.com";
        var password = "pws12345";
        var user = User.builder()
                .email(email)
                .build();

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));
        assertThrows(UserAlreadyExistsException.class, () -> createUserUseCase.process(email, password));

        verify(userRepository, times(1)).findByEmail(email);
        verifyNoMoreInteractions(userRepository);
    }
}
