package com.hitarth.my_expense_tracker.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hitarth.my_expense_tracker.Repository.UserRepository;
import com.hitarth.my_expense_tracker.dto.AuthResponse;
import com.hitarth.my_expense_tracker.dto.LoginRequest;
import com.hitarth.my_expense_tracker.dto.SignupRequest;
import com.hitarth.my_expense_tracker.entity.User;
import com.hitarth.my_expense_tracker.util.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwt;

    public AuthService(UserRepository repo, PasswordEncoder encoder, JwtUtil jwt) {
        this.repo = repo;
        this.encoder = encoder;
        this.jwt = jwt;
    }

    public AuthResponse signup(SignupRequest in) {
        log.info("Inside signup");
        System.out.println("Before saving user");
        if (repo.existsByUserName(in.username()) || repo.existsByEmail(in.email()))
            throw new IllegalArgumentException("Username/email already used");

        User u = new User();
        u.setUserName(in.username());
        u.setEmail(in.email());
        u.setPassword(encoder.encode(in.password()));
        repo.save(u);
        String token = jwt.generateToken(u.getUserName());
        log.info("token:",token);
        return AuthResponse.bearer(token);
    }

    public AuthResponse login(LoginRequest in) {
        User u = repo.findByUserName(in.username())
            .filter(x -> encoder.matches(in.password(), x.getPassword()))
            .orElseThrow(() -> new IllegalArgumentException("Bad credentials"));

        String token = jwt.generateToken(u.getUserName());
        return AuthResponse.bearer(token);
    }
}
