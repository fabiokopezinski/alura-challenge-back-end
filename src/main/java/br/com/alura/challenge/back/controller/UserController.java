package br.com.alura.challenge.back.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alura.challenge.back.annotations.UserCreateCodeStandard;
import br.com.alura.challenge.back.domain.dto.request.UserRequest;
import br.com.alura.challenge.back.domain.dto.response.UserResponse;
import br.com.alura.challenge.back.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@RequestMapping("/usuarios")
@RestController
@AllArgsConstructor
@Tag(name = "User")
public class UserController {
    
    private UserService userService;

    @PostMapping
    @UserCreateCodeStandard
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.save(userRequest));
    }
}
