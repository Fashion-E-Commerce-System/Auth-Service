package com.e_commerce_system.backend.dto.auth;

public record TokenResponse(
        String accessToken,
        String refreshToken
) {
}
