package br.com.hkanno.unittest.usecase.auth;

import br.com.hkanno.unittest.domain.auth.UserDetail;
import br.com.hkanno.unittest.exception.AuthenticationFailedException;
import br.com.hkanno.unittest.port.client.AuthenticationClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RequiredArgsConstructor
class LoginUseCaseImpl implements LoginUseCase {

    private final AuthenticationClient authenticationClient;

    @Override
    public UserDetail process(String email, String password) {
        try {
            return authenticationClient.login(email, password);
        } catch (AuthenticationFailedException ex) {
            log.error("Authentication failed", ex);
            throw ex;
        }
    }
}
