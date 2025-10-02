package com.hotel.checklist.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  private static final String SECURITY_SCHEME_NAME = "bearerAuth";

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("Checklist API")
            .version("v1")
            .description("API do Checklist (Render + Neon)"))
        // Exige JWT em todas as operações por padrão (cadeado no Swagger)
        .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME))
        // Define o esquema Bearer JWT para o botão "Authorize"
        .components(new Components().addSecuritySchemes(
            SECURITY_SCHEME_NAME,
            new SecurityScheme()
                .name("Authorization")
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
        ));
  }
}
