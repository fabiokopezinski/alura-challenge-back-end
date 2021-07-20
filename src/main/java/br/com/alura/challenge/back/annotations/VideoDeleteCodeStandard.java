package br.com.alura.challenge.back.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.http.MediaType;

import br.com.alura.challenge.back.exception.BusinessException.BusinessExceptionBody;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@Target({ ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@ApiResponses(value = {
                @ApiResponse(responseCode = "204", description = "Deletado com sucesso", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
                @ApiResponse(responseCode = "404", description = "Video não encontrado", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = BusinessExceptionBody.class))),
                @ApiResponse(responseCode = "500", description = "Sistema indisponivel", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)) })
@Operation(summary = Constants.VIDEO_DELETE_SUMMARY, description = Constants.VIDEO_DELETE_DESCRIPTION)
public @interface VideoDeleteCodeStandard {

}
