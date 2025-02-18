package com.example.securitydemo.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger-UI Configuration class
 * Tramite il metodo customOpenAPI() si configura lo swagger aggiungendo i requisiti di sicurezza.
 *
 * Inoltre è possibile definire le varie sezioni (definition) tra cui è possibile navigare.
 * Queste sezioni raggruppano le risorse API in base al ruolo dell'utente loggato.
 */
@Configuration
public class SwaggerConfig {


    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Security Demo Application API")
                        .version("1.0")
                        .description("API documentation for the Security Demo Application." +
                                " Please authenticate by select definition 'Authorization' -> 'Authenticate'"
                        )
                )
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new io.swagger.v3.oas.models.Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .in(SecurityScheme.In.HEADER)
                                .name("Authorization")
                        )
                );
    }

    @Bean
    public GroupedOpenApi authorizationApi() {
        return GroupedOpenApi.builder()
                .group("Authorization")
                .pathsToMatch("/authorization/**")
                .build();
    }

    @Bean
    public GroupedOpenApi adminApi() {
        return GroupedOpenApi.builder()
                .group("Admin")
                .pathsToMatch("/admin/**")
                .build();
    }

    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("User")
                .pathsToMatch("/user/**")
                .build();
    }
}
