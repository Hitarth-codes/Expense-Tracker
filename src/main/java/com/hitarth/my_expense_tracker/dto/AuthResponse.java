package com.hitarth.my_expense_tracker.dto;

public record AuthResponse(
        String accessToken,
        String tokenType) {

    public static AuthResponse bearer(String jwt) {
        return new AuthResponse(jwt, "Bearer");
    }
}
