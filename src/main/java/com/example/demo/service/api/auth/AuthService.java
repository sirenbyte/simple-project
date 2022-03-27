package com.example.demo.service.api.auth;

import com.example.demo.config.jwt.JwtAuthenticationResponse;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegistrationDto;

public interface AuthService {
    JwtAuthenticationResponse registration(RegistrationDto registrationDto);
    JwtAuthenticationResponse login(LoginDto loginDto);
}
