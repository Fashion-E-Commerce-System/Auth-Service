package com.e_commerce_system.backend.service;

import com.e_commerce_system.backend.domain.Member;
import com.e_commerce_system.backend.dto.auth.TokenResponse;
import com.e_commerce_system.backend.exception.DuplicateMemberException;
import com.e_commerce_system.backend.jwt.JwtTokenProvider;
import com.e_commerce_system.backend.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RedisTemplate<String, Object> redisTemplate;

    @Value("${jwt.refresh-token-validity-in-seconds}")
    private long refreshTokenValidityInSeconds;

    @Transactional
    public void signUp(String name, String email, String password) {
        if (memberRepository.findByEmail(email).isPresent()) {
            throw new DuplicateMemberException("Email already exists");
        }
        String encodedPassword = passwordEncoder.encode(password);
        Member member = new Member(null, name, encodedPassword, email);
        memberRepository.save(member);
    }

    @Transactional
    public TokenResponse login(String email, String password) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password"));
        if (!passwordEncoder.matches(password, member.getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        String accessToken = jwtTokenProvider.createAccessToken(email);
        String refreshToken = jwtTokenProvider.createRefreshToken(email);

        redisTemplate.opsForValue().set(
                email,
                refreshToken,
                refreshTokenValidityInSeconds,
                TimeUnit.SECONDS
        );

        return new TokenResponse(accessToken, refreshToken);
    }
}
