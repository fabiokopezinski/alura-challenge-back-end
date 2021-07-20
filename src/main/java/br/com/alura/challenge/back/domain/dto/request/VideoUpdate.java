package br.com.alura.challenge.back.domain.dto.request;

import org.hibernate.validator.constraints.URL;

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

    @URL(groups = { OnCreate.class, OnUpdate.class }, message = "Colocar em formato url")
    private String url;
}
