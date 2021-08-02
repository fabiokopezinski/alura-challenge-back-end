package br.com.alura.challenge.back.service;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import br.com.alura.challenge.back.domain.dto.response.UserResponse;
import br.com.alura.challenge.back.exception.BusinessException;
import br.com.alura.challenge.back.feature.ProfileScenarioFactory;
import br.com.alura.challenge.back.feature.UserScenarioFactory;
import br.com.alura.challenge.back.repository.ProfileRepository;
import br.com.alura.challenge.back.repository.UserRepository;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProfileRepository profileRepository;

    @Test
    @DisplayName("Procurar usuario pelo email")
    public void loadUserByUsername_WhenUsernameIsValid_ExpectedOk() {

        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(UserScenarioFactory.USER));

        UserDetails loadUserByUsername = userService.loadUserByUsername("teste@email.com");

        assertNotNull(loadUserByUsername);

        verify(userRepository).findByEmail(anyString());

    }

    @Test
    @DisplayName("Procurar usuario pelo email mas não existe")
    public void loadUserByUsername_WhenUsernameIsInvalid_ExpectedOk() {

        when(userRepository.findByEmail(anyString())).thenThrow(BusinessException.class);

        assertThrows(BusinessException.class, () -> userService.loadUserByUsername("teste"));
    }

    @Test
    @DisplayName("Criar usuário")
    public void save_WhenEmailIsValid_ExpectedCreate() {

        when(userRepository.findByEmail(any())).thenReturn(Optional.empty());

        when(profileRepository.findByName(any())).thenReturn(Optional.of(ProfileScenarioFactory.PROFILE));

        UserResponse save = userService.save(UserScenarioFactory.USER_REQUEST);

        assertNotNull(save);

        verify(userRepository).save(any());
    }

    @Test
    @DisplayName("Criar usuário, mas não existe o usuário")
    public void save_WhenProfileIsInvalid_ExpectedBusiness() {

        when(userRepository.findByEmail(any())).thenReturn(Optional.empty());

        when(profileRepository.findByName(any())).thenThrow(BusinessException.class);

        assertThrows(BusinessException.class, () -> userService.save(UserScenarioFactory.USER_REQUEST));

    }

    @Test
    @DisplayName("Criar usuário mas já existe no banco")
    public void save_WhenEmailIsInvalid_ExpectedCreate() {

        when(userRepository.findByEmail(anyString())).thenThrow(BusinessException.class);

        assertThrows(BusinessException.class, () -> userService.save(UserScenarioFactory.USER_REQUEST));
    }

}
