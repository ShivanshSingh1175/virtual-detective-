package com.virtualdetective.codebreaker.service;

import com.virtualdetective.codebreaker.dto.AuthResponse;
import com.virtualdetective.codebreaker.dto.LoginRequest;
import com.virtualdetective.codebreaker.dto.RegisterRequest;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}