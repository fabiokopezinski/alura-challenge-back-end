package br.com.alura.challenge.back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Api Alura Flix", description = "Plataforma de compartilhamento de vídeo", contact = @Contact(name = "Fábio Kopezinski", email = "fabiokopezinski@gmail.com"), version = "1.0.0"), 
         servers = {
                @Server(url="https://alura-flix-back.herokuapp.com/api/v1"),
                @Server(url = "http://localhost:8090/api/v1") })
public class Swagger {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().components(new Components().addSecuritySchemes("bearerAuth",
                new SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer")
                .bearerFormat("JWT")));
    }

}
