package br.com.alura.challenge.back.domain.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserRequest {
    
    @NotNull(message = "O campo 'email' no corpo da requisição")
    @Email(message = "Formato do email está errado")
    private String email;
    @NotNull(message = "O campo 'username' no corpo da requisição")
    private String username;
    @NotNull(message = "O campo 'password' no corpo da requisição")
    private String password;
}
