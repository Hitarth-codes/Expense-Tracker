package com.hitarth.my_expense_tracker.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hitarth.my_expense_tracker.dto.AuthResponse;
import com.hitarth.my_expense_tracker.dto.LoginRequest;
import com.hitarth.my_expense_tracker.dto.SignupRequest;
import com.hitarth.my_expense_tracker.service.AuthService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RequestMapping("/api/myexpense")
@RestController 
@Slf4j
public class AuthController {

  private final AuthService svc;

  public AuthController(AuthService svc) { 
    System.out.println(">>> AuthController initialized");
    this.svc = svc; 
  }

  @PostMapping("/signup") @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<AuthResponse> register(@Valid @RequestBody SignupRequest in) {
    log.info("Inside register");
    AuthResponse resp = svc.signup(in);
    return ResponseEntity.ok(resp); 
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest in) { 
    log.info("Inside login");
    AuthResponse resp = svc.login(in);
    return ResponseEntity.ok(resp);
  }

//  @PostMapping("/changepassword")
//  public void changePwd(@AuthenticationPrincipal UserDetails user,
//                        @Valid @RequestBody ChangePasswordRequest req) {
//    svc.changePassword(user.getUsername(), req);
}

