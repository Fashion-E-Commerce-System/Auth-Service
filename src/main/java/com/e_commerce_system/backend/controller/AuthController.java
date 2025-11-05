package com.e_commerce_system.backend.controller;

import com.e_commerce_system.backend.dto.auth.LoginRequest;
import com.e_commerce_system.backend.dto.auth.RefreshTokenRequest;
import com.e_commerce_system.backend.dto.auth.TokenResponse;
import com.e_commerce_system.backend.dto.MemberRequest;
import com.e_commerce_system.backend.service.MemberService;
import com.e_commerce_system.backend.service.TokenService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class AuthController {

    private final MemberService memberService;
    private final TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody MemberRequest memberRequest) {
        memberService.signUp(memberRequest.getName(), memberRequest.getEmail(), memberRequest.getPassword());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest loginRequest) {
        TokenResponse tokenResponse = memberService.login(loginRequest.email(), loginRequest.password());
        return ResponseEntity.ok(tokenResponse);
    }

    @PostMapping("/refresh")
    public ResponseEntity<TokenResponse> refresh(@Valid @RequestBody RefreshTokenRequest refreshTokenRequest) {
        TokenResponse tokenResponse = tokenService.refreshAccessToken(refreshTokenRequest.refreshToken());
        return ResponseEntity.ok(tokenResponse);
    }
}
