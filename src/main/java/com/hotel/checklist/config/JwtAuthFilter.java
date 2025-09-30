package com.hotel.checklist.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;

public class JwtAuthFilter extends OncePerRequestFilter {
  private final JwtUtil jwt;
  public JwtAuthFilter(JwtUtil jwt){ this.jwt = jwt; }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {

    String header = request.getHeader("Authorization");
    if (header != null && header.startsWith("Bearer ")) {
      String token = header.substring(7);
      try {
        Claims c = jwt.parse(token).getBody();
        var auth = new UsernamePasswordAuthenticationToken(
            c.getSubject(), null,
            java.util.List.of(new SimpleGrantedAuthority("ROLE_" + c.get("role", String.class)))
        );
        auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(auth);
      } catch (Exception ignored) {}
    }
    chain.doFilter(request, response);
  }
}

