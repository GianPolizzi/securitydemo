package com.example.securitydemo.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Swagger-UI Configuration class
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
                                " Please authenticate by select definition 'Authorization' -> 'Authenticate'")
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
