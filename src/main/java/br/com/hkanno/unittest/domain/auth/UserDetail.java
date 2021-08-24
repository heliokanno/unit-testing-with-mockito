package br.com.hkanno.unittest.domain.auth;

import br.com.hkanno.unittest.domain.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDetail {

    private User user;

    private String token;

}
