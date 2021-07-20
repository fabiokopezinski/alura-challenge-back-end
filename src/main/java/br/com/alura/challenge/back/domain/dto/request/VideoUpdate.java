package br.com.alura.challenge.back.domain.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.alura.challenge.back.validations.OnCreate;
import br.com.alura.challenge.back.validations.OnUpdate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class VideoUpdate {

    private String description;

    private String url;
}
