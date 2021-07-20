package br.com.alura.challenge.back.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VideoRequest {

    @NotBlank(message = "O title está inválido")
    @NotNull(message = "O title está inválido")
    private String title;

    @NotBlank(message = "O description está inválido")
    @NotNull(message = "O description está inválido")
    private String description;

    @NotBlank(message = "O url está inválido")
    @NotNull(message = "O url está inválido")
    private String url;
}
