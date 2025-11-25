package com.ecommerce.backend.controller;

import com.ecommerce.backend.dto.LoginRequest;
import com.ecommerce.backend.dto.TokenRequest;
import com.ecommerce.backend.dto.TokenResponse;
import com.ecommerce.backend.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@RequestBody LoginRequest request,
                                               Authentication authentication,
                                               @RequestHeader(value = "Authorization", required = false) String accessToken) {

        if (authentication != null && authentication.isAuthenticated() && accessToken != null && accessToken.startsWith("Bearer ")) {
            authService.logout(accessToken.substring(7));
        }


        TokenResponse tokenResponse = authService.login(request);
        return ResponseEntity.ok(tokenResponse);
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String accessToken) {
        if (accessToken != null && accessToken.startsWith("Bearer ")) {
            authService.logout(accessToken.substring(7));
        }
        return ResponseEntity.ok().build();
    }


    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(@RequestBody TokenRequest request) {
        TokenResponse tokenResponse = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(tokenResponse);
    }
}