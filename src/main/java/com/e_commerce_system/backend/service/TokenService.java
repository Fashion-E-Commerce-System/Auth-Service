package com.e_commerce_system.backend.service;

import com.e_commerce_system.backend.dto.auth.TokenResponse;
import com.e_commerce_system.backend.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, Object> redisTemplate;

    public TokenResponse refreshAccessToken(String refreshToken) {
        if (!jwtTokenProvider.validateToken(refreshToken)) {
            throw new IllegalArgumentException("Invalid refresh token");
        }

        String email = jwtTokenProvider.getEmail(refreshToken);
        String storedRefreshToken = (String) redisTemplate.opsForValue().get(email);

        if (storedRefreshToken == null || !storedRefreshToken.equals(refreshToken)) {
            throw new IllegalArgumentException("Invalid refresh token");
        }

        String newAccessToken = jwtTokenProvider.createAccessToken(email);
        return new TokenResponse(newAccessToken, refreshToken);
    }
}
