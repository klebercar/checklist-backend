package com.hotel.checklist.config;
import io.jsonwebtoken.*; import io.jsonwebtoken.security.Keys; import java.util.Date; import javax.crypto.SecretKey;
public class JwtUtil {
  private final SecretKey key; private final long expirationMs;
  public JwtUtil(String secret,long expirationMs){ this.key=Keys.hmacShaKeyFor(secret.getBytes()); this.expirationMs=expirationMs; }
  public String generate(String subject,String role){ return Jwts.builder().setSubject(subject).claim("role",role).setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis()+expirationMs)).signWith(key).compact(); }
  public Jws<Claims> parse(String token){ return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token); }
}
