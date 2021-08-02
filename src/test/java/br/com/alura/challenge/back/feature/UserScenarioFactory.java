package br.com.alura.challenge.back.feature;


import java.util.Arrays;

import br.com.alura.challenge.back.domain.User;
import br.com.alura.challenge.back.domain.dto.request.UserRequest;
import br.com.alura.challenge.back.domain.dto.response.UserResponse;

public class UserScenarioFactory {
    
    public static final User USER = loadUser();

    public static final UserRequest USER_REQUEST = loadUserRequest();

    public static final UserResponse USER_RESPONSE = loadUserResponse();

    private static User loadUser() {
        
        return User.builder()
                .email("teste@email.com")
                .password("124578")
                .profiles(Arrays.asList(ProfileScenarioFactory.PROFILE))
        .build();
    }

    private static UserResponse loadUserResponse() {
        return new UserResponse("teste@email.com", "teste");
    }

    private static UserRequest loadUserRequest() {
        return new UserRequest("teste@email.com","teste","124578");
    }
}
