package com.hitarth.my_expense_tracker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public record SignupRequest(
        @NotBlank(message = "Username is mandatory")
        @Size(min = 3, max = 50)
        String username,

        @NotBlank(message = "Email is mandatory")
        @Email
        String email,
        
        @NotBlank(message = "Password is mandatory")
        @Size(min = 8, max = 120)
        String password) {}
