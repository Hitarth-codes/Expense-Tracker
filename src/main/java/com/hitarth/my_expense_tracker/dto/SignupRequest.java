package com.hitarth.my_expense_tracker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/** Payload for POST /api/auth/signup */
public record SignupRequest(
        @NotBlank
        @Size(min = 3, max = 50)
        String username,

        @NotBlank
        @Email
        String email,
        
        @NotBlank
        @Size(min = 8, max = 120)
        String password) {}
