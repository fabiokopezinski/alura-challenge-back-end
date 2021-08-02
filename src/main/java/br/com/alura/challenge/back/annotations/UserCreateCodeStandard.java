package br.com.alura.challenge.back.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.http.MediaType;

import br.com.alura.challenge.back.domain.dto.response.UserResponse;
import br.com.alura.challenge.back.exception.BusinessException.BusinessExceptionBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Usuário logado", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UserResponse.class))),
        @ApiResponse(responseCode = "400", description = "Já possui cadastro", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BusinessExceptionBody.class))),
        @ApiResponse(responseCode = "500", description = "Sistema indisponível", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) })
@Operation(summary = Constants.USER_CREATE, description = Constants.USER_CREATE_DESCRIPTION)
public @interface UserCreateCodeStandard {
    
}
