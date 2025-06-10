package com.virtualdetective.codebreaker.controller;

import com.virtualdetective.codebreaker.dto.AuthRequest;
import com.virtualdetective.codebreaker.dto.AuthResponse;
import com.virtualdetective.codebreaker.dto.RegisterRequest;
import com.virtualdetective.codebreaker.model.User;
import com.virtualdetective.codebreaker.security.JwtService;
import com.virtualdetective.codebreaker.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        User user = authService.register(request);
        String token = jwtService.generateToken(user.getUsername());
        return ResponseEntity.ok(new AuthResponse(token, user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        User user = authService.authenticate(request);
        String token = jwtService.generateToken(user.getUsername());
        return ResponseEntity.ok(new AuthResponse(token, user));
    }
} 