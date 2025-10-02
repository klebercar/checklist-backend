package com.hotel.checklist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SwaggerAllowConfig {

  private static final String[] SWAGGER_WHITELIST = {
      "/v3/api-docs/**",
      "/swagger-ui.html",
      "/swagger-ui/**"
  };

  /**
   * Cadeia de segurança com prioridade alta (0) só para as rotas do Swagger.
   * Permite tudo nelas e mantém sua outra cadeia para o restante das rotas.
   */
  @Order(0)
  @Bean
  public SecurityFilterChain swaggerSecurityFilterChain(HttpSecurity http) throws Exception {
    http
      .securityMatcher(SWAGGER_WHITELIST)
      .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
      .csrf(csrf -> csrf.disable());
    return http.build();
  }
}
