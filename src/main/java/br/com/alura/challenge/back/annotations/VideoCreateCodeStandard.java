package br.com.alura.challenge.back.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.http.MediaType;

import br.com.alura.challenge.back.domain.dto.response.VideoResponse;
import br.com.alura.challenge.back.exception.BusinessException.BusinessExceptionBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Cadastrado com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = VideoResponse.class))),
        @ApiResponse(responseCode = "400", description = "Video já existente", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BusinessExceptionBody.class))),
        @ApiResponse(responseCode = "500", description = "Sistema indisponivel", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) })
@Operation(summary = Constants.VIDEO_SAVE_SUMMARY, description = Constants.VIDEO_SAVE_DESCRIPTION, security = @SecurityRequirement(name = "bearerAuth"))
public @interface VideoCreateCodeStandard {

}
