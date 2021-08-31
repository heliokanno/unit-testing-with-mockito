package br.com.hmk.unittest.usecase.auth;

import br.com.hmk.unittest.exception.AuthenticationFailedException;
import br.com.hmk.unittest.port.client.AuthDetail;
import br.com.hmk.unittest.port.client.AuthenticationClient;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@ExtendWith(MockitoExtension.class)
class LoginUseCaseTest {

    @InjectMocks
    LoginUseCaseImpl loginUseCase;

    @Mock
    AuthenticationClient authenticationClient;

    @Test
    void shouldReturnUserDetailOnSuccess() {
        var email = "test@mock.com";
        var password = "pws12345";
        var authDetail = AuthDetail.builder()
                .token("eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9")
                .build();

        when(authenticationClient.authenticate(email, password)).thenReturn(authDetail);
        var auth = loginUseCase.process(email, password);
        assertThat(auth.getToken()).isEqualTo(authDetail.getToken());

        verify(authenticationClient, times(1)).authenticate(email, password);
        verifyNoMoreInteractions(authenticationClient);
    }

    @Test
    void shouldThrowAuthenticationFailedExceptionOnFailure() {
        var email = "test@mock.com";
        var password = "pws12345";

        when(authenticationClient.authenticate(email, password)).thenThrow(new AuthenticationFailedException());
        assertThrows(AuthenticationFailedException.class, () -> loginUseCase.process(email, password));

        verify(authenticationClient, times(1)).authenticate(email, password);
        verifyNoMoreInteractions(authenticationClient);
    }
}
