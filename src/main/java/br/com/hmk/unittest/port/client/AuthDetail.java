package br.com.hmk.unittest.port.client;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthDetail {
    private String token;
}
