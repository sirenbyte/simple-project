package com.example.demo.controller;

import com.example.demo.config.jwt.JwtAuthenticationResponse;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegistrationDto;
import com.example.demo.service.api.auth.AuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(value = "API для авторизаций")
@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthService authService;

    @ApiOperation(value = "Регистрация пользователя", notes = "Регистрация пользователя")
    @PostMapping("/register")
    public ResponseEntity<JwtAuthenticationResponse> registerUser(@RequestBody @Valid RegistrationDto registrationRequest) {
        return ResponseEntity.ok(authService.registration(registrationRequest));
    }

    @ApiOperation(value = "Вход пользователя", notes = "Вход пользователя")
    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationResponse> loginUser(@RequestBody @Valid LoginDto loginDto){
        return ResponseEntity.ok(authService.login(loginDto));
    }
}
