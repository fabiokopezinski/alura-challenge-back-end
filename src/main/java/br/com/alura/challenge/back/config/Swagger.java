package br.com.alura.challenge.back.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Api Alura Flix", description = "Plataforma de compartilhamento de vídeo", contact = @Contact(name = "Fábio Kopezinski", email = "fabiokopezinski@gmail.com"), version = "1.0.0"), servers = {
                @Server(url = "http://localhost:8080/api/v1") })
public class Swagger {

}
