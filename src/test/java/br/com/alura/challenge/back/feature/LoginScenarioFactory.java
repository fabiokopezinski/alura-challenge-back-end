package br.com.alura.challenge.back.feature;

import br.com.alura.challenge.back.domain.dto.request.LoginRequest;
import br.com.alura.challenge.back.domain.dto.response.LoginResponse;

public class LoginScenarioFactory {
  
    public static final LoginRequest LOGIN = loadRequest();

    public static final LoginResponse LOGIN_RESPONSE = loadResponse();

    private static LoginRequest loadRequest() {
        return new LoginRequest("teste@email.com", "124578");
    }

    private static LoginResponse loadResponse() {
        return new LoginResponse("fkdasofkoadskf0oadsf", "Bearer");
    }
}
