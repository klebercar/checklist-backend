package com.hotel.checklist.config;
import org.springframework.context.annotation.*; import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity; import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.*; import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Value; import org.springframework.web.cors.*; import java.util.List;

@Configuration @EnableMethodSecurity
public class SecurityConfig {
  @Value("${app.jwt.secret}") private String secret; @Value("${app.jwt.expirationMs}") private long expirationMs;

  @Bean public JwtUtil jwtUtil(){ return new JwtUtil(secret, expirationMs); }
  @Bean public PasswordEncoder passwordEncoder(){ return new BCryptPasswordEncoder(); }

  @Bean public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.csrf(cs->cs.disable()).cors(c->c.configurationSource(corsSource()))
      .sessionManagement(sm->sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .authorizeHttpRequests(reg->reg.requestMatchers("/api/auth/**").permitAll().anyRequest().authenticated())
      .addFilterBefore(new JwtAuthFilter(jwtUtil()), UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
  @Bean CorsConfigurationSource corsSource(){
    var c=new CorsConfiguration(); c.setAllowedOrigins(List.of("*"));
    c.setAllowedMethods(List.of("GET","POST","PUT","DELETE","OPTIONS"));
    c.setAllowedHeaders(List.of("*"));
    var s=new UrlBasedCorsConfigurationSource(); s.registerCorsConfiguration("/**", c); return s;
  }
}
