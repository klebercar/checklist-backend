package com.hotel.checklist.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private static final String[] SWAGGER_WHITELIST = {
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/swagger-ui/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // CORS global (CorsConfig)
            .cors(Customizer.withDefaults())
            // CSRF desabilitado (stateless com JWT)
            .csrf(csrf -> csrf.disable())
            // Stateless
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            // Autorização
            .authorizeHttpRequests(auth -> auth
                // Pré-flight
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                // Público
                .requestMatchers("/api/auth/**").permitAll()
                .requestMatchers(SWAGGER_WHITELIST).permitAll()
                // Demais precisam de auth
                .anyRequest().authenticated()
            );

        // Se você usa filtro JWT, insira aqui (exemplo):
        // http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
