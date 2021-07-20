package br.com.alura.challenge.back.domain.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.alura.challenge.back.validations.OnCreate;
import br.com.alura.challenge.back.validations.OnUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VideoRequest {

    @NotBlank(groups = { OnCreate.class, OnUpdate.class }, message = "O title está inválido")
    @NotEmpty(groups = { OnCreate.class, OnUpdate.class }, message = "O title está inválido")
    @NotNull(groups = { OnCreate.class, OnUpdate.class }, message = "O title está inválido")
    private String title;

    @NotBlank(groups = { OnCreate.class, OnUpdate.class }, message = "O description está inválido")
    @NotEmpty(groups = { OnCreate.class, OnUpdate.class }, message = "O title está inválido")
    @NotNull(groups = { OnCreate.class, OnUpdate.class }, message = "O description está inválido")
    private String description;

    @NotBlank(groups = { OnCreate.class, OnUpdate.class }, message = "O url está inválido")
    @NotNull(groups = { OnCreate.class, OnUpdate.class }, message = "O url está inválido")
    @NotEmpty(groups = { OnCreate.class, OnUpdate.class }, message = "O title está inválido")
    private String url;
}
