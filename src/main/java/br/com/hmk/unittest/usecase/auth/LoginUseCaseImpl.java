package br.com.hmk.unittest.usecase.auth;

import br.com.hmk.unittest.exception.AuthenticationFailedException;
import br.com.hmk.unittest.port.client.AuthDetail;
import br.com.hmk.unittest.port.client.AuthenticationClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
class LoginUseCaseImpl implements LoginUseCase {

    private final AuthenticationClient authenticationClient;

    @Override
    public AuthDetail process(String email, String password) {
        try {
            return authenticationClient.authenticate(email, password);
        } catch (AuthenticationFailedException ex) {
            log.error("Authentication failed", ex);
            throw ex;
        }
    }
}
