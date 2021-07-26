package br.com.alura.challenge.back.domain.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.alura.challenge.back.validations.OnCreate;
import br.com.alura.challenge.back.validations.OnUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CategoryRequest {
    
    @NotBlank(groups = { OnCreate.class, OnUpdate.class }, message = "O title está inválido")
    @NotEmpty(groups = { OnCreate.class, OnUpdate.class }, message = "O title está inválido")
    @NotNull(groups = { OnCreate.class, OnUpdate.class }, message = "O title está inválido")
    private String title;

    @NotBlank(groups = { OnCreate.class, OnUpdate.class }, message = "O color está inválido")
    @NotEmpty(groups = { OnCreate.class, OnUpdate.class }, message = "O color está inválido")
    @NotNull(groups = { OnCreate.class, OnUpdate.class }, message = "O color está inválido")
    private String color;
}
