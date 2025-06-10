package com.virtualdetective.codebreaker.controller;

import com.virtualdetective.codebreaker.dto.AuthRequest;
import com.virtualdetective.codebreaker.dto.AuthResponse;
import com.virtualdetective.codebreaker.dto.RegisterRequest;
import com.virtualdetective.codebreaker.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody AuthRequest request) {
        AuthResponse response = authService.login(new com.virtualdetective.codebreaker.dto.LoginRequest(request.getUsername(), request.getPassword()));
        return ResponseEntity.ok(response);
    }
}