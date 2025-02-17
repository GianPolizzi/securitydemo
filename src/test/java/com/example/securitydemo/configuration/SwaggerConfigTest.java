package com.example.securitydemo.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class SwaggerConfigTest {

    private final SwaggerConfig swaggerConfig = new SwaggerConfig();

    @Test
    void customOpenAPI() {
        OpenAPI openAPI = swaggerConfig.customOpenAPI();

        assertNotNull(openAPI);

        Info info = openAPI.getInfo();
        assertNotNull(info);
        assertEquals("Security Demo Application API", info.getTitle());
        assertEquals("1.0", info.getVersion());
        assertTrue(info.getDescription().contains("API documentation for the Security Demo Application"));

        Components components = openAPI.getComponents();
        assertNotNull(components);

        SecurityScheme securityScheme = components.getSecuritySchemes().get("bearerAuth");
        assertNotNull(securityScheme);
        assertEquals(SecurityScheme.Type.HTTP, securityScheme.getType());
        assertEquals("bearer", securityScheme.getScheme());
        assertEquals("JWT", securityScheme.getBearerFormat());
        assertEquals(SecurityScheme.In.HEADER, securityScheme.getIn());
        assertEquals("Authorization", securityScheme.getName());

        SecurityRequirement securityRequirement = openAPI.getSecurity().get(0);
        assertNotNull(securityRequirement);
        assertTrue(securityRequirement.containsKey("bearerAuth"));
    }

    @Test
    void authorizationApi() {
        org.springdoc.core.models.GroupedOpenApi groupedOpenApi = swaggerConfig.authorizationApi();

        assertNotNull(groupedOpenApi);
        assertEquals("Authorization", groupedOpenApi.getGroup());
        assertTrue(groupedOpenApi.getPathsToMatch().contains("/authorization/**"));
    }

    @Test
    void adminApi() {
        org.springdoc.core.models.GroupedOpenApi groupedOpenApi = swaggerConfig.adminApi();

        assertNotNull(groupedOpenApi);
        assertEquals("Admin", groupedOpenApi.getGroup());
        assertTrue(groupedOpenApi.getPathsToMatch().contains("/admin/**"));
    }

    @Test
    void userApi() {
        org.springdoc.core.models.GroupedOpenApi groupedOpenApi = swaggerConfig.userApi();

        assertNotNull(groupedOpenApi);
        assertEquals("User", groupedOpenApi.getGroup());
        assertTrue(groupedOpenApi.getPathsToMatch().contains("/user/**"));
    }
}