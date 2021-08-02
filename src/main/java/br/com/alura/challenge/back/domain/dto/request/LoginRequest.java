package br.com.alura.challenge.back.domain.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LoginRequest {
    
    @NotNull(message = "O campo 'email' no corpo da requisição")
    @Email(message = "Formato do email está errado")
    private String email;

    @NotNull(message = "O campo 'password' no corpo da requisição")
    private String password;
}
