package br.com.alura.challenge.back.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.challenge.back.annotations.LoginPostCodeStandard;
import br.com.alura.challenge.back.domain.dto.request.LoginRequest;
import br.com.alura.challenge.back.domain.dto.response.LoginResponse;
import br.com.alura.challenge.back.service.LoginService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RequestMapping("/login")
@AllArgsConstructor
@RestController
@Tag(name = "Login")
public class LoginController {

    private LoginService loginService;
    
    @PostMapping
    @LoginPostCodeStandard
    public ResponseEntity<LoginResponse> auth(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(loginService.auth(loginRequest));
    }    
}
