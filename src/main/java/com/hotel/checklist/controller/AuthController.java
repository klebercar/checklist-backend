package com.hotel.checklist.controller;
import com.hotel.checklist.repo.*; import com.hotel.checklist.dto.*; import com.hotel.checklist.entity.*; import com.hotel.checklist.config.*; import lombok.RequiredArgsConstructor;
import org.springframework.http.*; import org.springframework.security.access.prepost.PreAuthorize; import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder; import org.springframework.web.bind.annotation.*; import org.springframework.web.server.ResponseStatusException;
import java.util.Map;
@RestController @RequestMapping("/api/auth") @RequiredArgsConstructor
public class AuthController {
  private final UserRepository users; private final PasswordEncoder encoder; private final JwtUtil jwt;
  @PostMapping("/login") public Map<String,Object> login(@RequestBody LoginReq req){
    var u=users.findByEmail(req.email()).orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED));
    if(!encoder.matches(req.password(), u.getPasswordHash())) throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
    String token=jwt.generate(u.getEmail(), u.getRole());
    return Map.of("token",token,"role",u.getRole(),"name",u.getName());
  }
  @PostMapping("/signup") @PreAuthorize("hasRole('ADMIN')") public void signup(@RequestBody SignupReq req){
    users.save(new User(null, req.name(), req.email(), encoder.encode(req.password()), req.role()));
  }
  @GetMapping("/me") public Map<String,Object> me(Authentication auth){ return Map.of("email", auth.getPrincipal()); }
}
