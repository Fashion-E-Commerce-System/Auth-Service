package com.ecommerce.backend.security;

import com.ecommerce.backend.dto.TokenResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtTokenProvider {

    private final Key key;
    private final long accessTokenValidityInMilliseconds;
    private final long refreshTokenValidityInMilliseconds;
    private static final Logger log = LoggerFactory.getLogger(JwtTokenProvider.class);

    public JwtTokenProvider(@Value("${jwt.secret}") String secret,
                            @Value("${jwt.access-token-validity-in-seconds}") long accessTokenValidityInSeconds,
                            @Value("${jwt.refresh-token-validity-in-seconds}") long refreshTokenValidityInSeconds) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
        this.accessTokenValidityInMilliseconds = accessTokenValidityInSeconds * 1000;
        this.refreshTokenValidityInMilliseconds = refreshTokenValidityInSeconds * 1000;
    }

    public TokenResponse createToken(Authentication authentication) {
        String accessToken = createAccessToken(authentication);
        String refreshToken = createRefreshToken(authentication);
        return new TokenResponse(accessToken, refreshToken);
    }

    private String createAccessToken(Authentication authentication) {
        return createToken(authentication, accessTokenValidityInMilliseconds);
    }

    private String createRefreshToken(Authentication authentication) {
        return createToken(authentication, refreshTokenValidityInMilliseconds);
    }

    private String createToken(Authentication authentication, long validityInMilliseconds) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityInMilliseconds);
        
        String authorities = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("roles", authorities)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.warn("Invalid JWT token: {}", e.getMessage());
            return false;
        }
    }

    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        String email = claims.getSubject();
        List<SimpleGrantedAuthority> authorities = List.of(claims.get("roles").toString().split(","))
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new UsernamePasswordAuthenticationToken(email, null, authorities);
    }

    public String getEmail(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody().getSubject();
    }
    
    public long getRefreshTokenValidityInSeconds() {
        return refreshTokenValidityInMilliseconds / 1000;
    }

    public Long getExpiration(String accessToken) {
        Date expiration = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(accessToken).getBody().getExpiration();
        long now = new Date().getTime();
        return (expiration.getTime() - now);
    }
}
