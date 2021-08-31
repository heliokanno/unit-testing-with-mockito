package br.com.hmk.unittest.usecase.user;

import br.com.hmk.unittest.domain.User;
import br.com.hmk.unittest.exception.UserNotFoundException;
import br.com.hmk.unittest.port.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
class FindUserByEmailUseCaseTest {

    @InjectMocks
    FindUserByEmailUseCaseImpl findUserByEmailUseCase;

    @Mock
    UserRepository userRepository;

    @Test
    void shouldReturnUserWhenUserExists() {
        var email = "test@mock.com";
        var persistedUser = User.builder()
                .id(1L)
                .email(email)
                .password("pws12345")
                .build();

        when(userRepository.findByEmail(email)).thenReturn(Optional.of(persistedUser));

        var user = findUserByEmailUseCase.process(email);
        assertThat(user.getId()).isEqualTo(persistedUser.getId());
        assertThat(user.getEmail()).isEqualTo(persistedUser.getEmail());
        assertThat(user.getPassword()).isEqualTo(persistedUser.getPassword());

        verify(userRepository, times(1)).findByEmail(email);
        verifyNoMoreInteractions(userRepository);
    }

    @Test
    void shouldThrowUserNotFoundExceptionWhenUserNotExists() {
        var email = "test@mock.com";
        assertThrows(UserNotFoundException.class, () -> findUserByEmailUseCase.process(email));

        verify(userRepository, times(1)).findByEmail(email);
        verifyNoMoreInteractions(userRepository);
    }
}
