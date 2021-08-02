package br.com.alura.challenge.back.validations;

import org.springframework.http.HttpStatus;

import br.com.alura.challenge.back.exception.BusinessException;

public enum Message {

    VIDEO_EXIST("Video já existe", HttpStatus.BAD_REQUEST),
    IS_PRESENT_USER("Usuário existente",HttpStatus.BAD_REQUEST),
    NAME_PROFILE_NOT_FOUND("Perfil não encontrado",HttpStatus.NOT_FOUND),
    TOKEN_ERROR("Erro ao validar o token", HttpStatus.UNAUTHORIZED),
    CATEGORY("Categoria já existe",HttpStatus.BAD_REQUEST),
    NOT_FOUND_CATEGORY("Categoria não encontrado",HttpStatus.NOT_FOUND),
    NOT_FOUND_VIDEO("Video não encontrado", HttpStatus.NOT_FOUND), 
    NOT_FOT_USER_PERMISSION("Email ou senha inválida", HttpStatus.UNAUTHORIZED);

    private String value;
    private String description;
    private HttpStatus statusCode;

    private Message(String value, HttpStatus statusCode) {
        this.value = value;
        this.statusCode = statusCode;
    }

    private Message(String value, String description, HttpStatus statusCode) {
        this.value = value;
        this.description = description;
        this.statusCode = statusCode;
    }

    private Message(String value) {
        this.value = value;
    }

    public String getMessage() {
        return this.value;
    }

    public HttpStatus getStatus() {
        return this.statusCode;
    }

    public String getDescription() {
        return description;
    }

    public BusinessException asBusinessException() {
        return BusinessException.builder().httpStatusCode(this.getStatus())
                .code(String.valueOf(this.getStatus().value())).message(this.getMessage())
                .description(this.getDescription()).build();
    }
}
