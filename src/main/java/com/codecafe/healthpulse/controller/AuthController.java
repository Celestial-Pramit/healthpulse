package com.codecafe.healthpulse.controller;

import com.codecafe.healthpulse.dto.LoginRequest;
import com.codecafe.healthpulse.security.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // POST http://localhost:9090/auth/login   body: {"username":"admin","password":"admin123"}
    @PostMapping("auth/login")
    public Map<String, String> login(@Valid @RequestBody LoginRequest request) {
        UserDetails user;
        try {
            user = userDetailsService.loadUserByUsername(request.getUsername());
        } catch (UsernameNotFoundException e) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid username or password");
        }
        String role = user.getAuthorities().iterator().next().getAuthority();  // e.g. ROLE_ADMIN
        return Map.of("token", jwtUtil.generateToken(user.getUsername(), role));
    }
}
