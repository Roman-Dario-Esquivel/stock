package com.stocktienda.stock.springdoc;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {
     @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI().components(new Components()
                .addSecuritySchemes("bearer-key",
                        new SecurityScheme().
                                type(SecurityScheme.Type.HTTP).
                                scheme("bearer").
                                bearerFormat("JWT")))
                .info(new Info()
                        .title("Stock API")
                        .description("API Rest de la aplicación Stosck que contiene las funcionalidades de CRUD " +
                                "de Usuarios como así también login y autenticación mediante Token")
                        .contact(new Contact()
                                .name("")
                                .email(""))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("")));
    }
}
