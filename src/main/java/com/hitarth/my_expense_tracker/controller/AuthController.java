package com.hitarth.my_expense_tracker.controller;

import org.springframework.http.HttpStatus;
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
  public AuthResponse register(@Valid @RequestBody SignupRequest in) {
    log.info("Inside register");
    return svc.signup(in); 
  }

  @PostMapping("/login")
  public AuthResponse login(@Valid @RequestBody LoginRequest in) { 
    return svc.login(in); 
  }

//  @PostMapping("/changepassword")
//  public void changePwd(@AuthenticationPrincipal UserDetails user,
//                        @Valid @RequestBody ChangePasswordRequest req) {
//    svc.changePassword(user.getUsername(), req);
}

