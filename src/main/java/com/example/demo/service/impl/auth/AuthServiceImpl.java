package com.example.demo.service.impl.auth;

import com.example.demo.config.jwt.JwtAuthenticationResponse;
import com.example.demo.config.jwt.JwtProvider;
import com.example.demo.dto.LoginDto;
import com.example.demo.dto.RegistrationDto;
import com.example.demo.entity.User;
import com.example.demo.service.api.UserService;
import com.example.demo.service.api.auth.AuthService;
import com.example.demo.util.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService userService;
    private final JwtProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    private User mapToUser(RegistrationDto dto) {
        User user = new User();
        user.setFio(dto.getFio());
        user.setIin(dto.getIin());
        user.setPhone(dto.getPhone());
        user.setAddress(dto.getAddress());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        return userService.save(user);
    }

    @Override
    public JwtAuthenticationResponse registration(RegistrationDto dto) {
        return Optional.ofNullable(dto.getPhone())
                .filter(it -> !userService.existByPhone(dto.getPhone()))
                .map(it -> mapToUser(dto))
                .map(it -> new JwtAuthenticationResponse(jwtProvider.generateToken(it.getPhone())))
                .orElseThrow(()->new EntityNotFoundException("User exist"));
    }

    @Override
    public JwtAuthenticationResponse login(LoginDto dto) {
        User user = userService.getByPhone(dto.getPhone());
        return Optional.ofNullable(dto.getPassword())
                .filter(it -> passwordEncoder.matches(dto.getPassword(), user.getPassword()))
                .map(it -> new JwtAuthenticationResponse(jwtProvider.generateToken(user.getPhone())))
                .orElseThrow(() -> new EntityNotFoundException("Password wrong"));
    }
}
