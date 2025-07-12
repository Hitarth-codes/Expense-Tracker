package com.hitarth.my_expense_tracker.dto;

import jakarta.validation.constraints.NotBlank;

/** Payload for POST /api/auth/login */
public record LoginRequest(
        @NotBlank String username,
        @NotBlank String password) {}