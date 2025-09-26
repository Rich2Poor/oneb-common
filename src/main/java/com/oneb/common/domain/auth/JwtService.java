package com.oneb.common.domain.auth;

import com.oneb.common.config.properties.JwtProperties;
import com.oneb.common.domain.user.dto.UserInfo;
import com.oneb.common.domain.user.enums.Role;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Service for JWT token operations
 */
@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnBean(JwtProperties.class)
public class JwtService {

    private final JwtProperties jwtProperties;

    /**
     * Get the signing key for JWT operations
     */
    private SecretKey getSigningKey() {
        byte[] keyBytes = jwtProperties.getSecret().getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    private SecretKey getRefreshSigningKey() {
        byte[] keyBytes = jwtProperties.getRefreshSecret().getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * Extract all claims from JWT token
     */
    private Claims extractAllClaims(String token, SecretKey key) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateAccessToken(String token) {
        return validateToken(token, getSigningKey());
    }

    public boolean validateRefreshToken(String refreshToken) {
        return validateToken(refreshToken, getRefreshSigningKey());
    }

    /**
     * Validate JWT token
     */
    public boolean validateToken(String token, SecretKey key) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (ExpiredJwtException e) {
            log.warn("JWT token is expired: {}", e.getMessage());
            return false;
        } catch (UnsupportedJwtException e) {
            log.warn("JWT token is unsupported: {}", e.getMessage());
            return false;
        } catch (MalformedJwtException e) {
            log.warn("JWT token is malformed: {}", e.getMessage());
            return false;
        } catch (SecurityException e) {
            log.warn("JWT signature validation failed: {}", e.getMessage());
            return false;
        } catch (IllegalArgumentException e) {
            log.warn("JWT token compact of handler are invalid: {}", e.getMessage());
            return false;
        } catch (Exception e) {
            log.error("JWT token validation failed: {}", e.getMessage());
            return false;
        }
    }

    /**
     * Extract user information from JWT token
     */
    public UserInfo extractUserInfo(String token) {
        return extractUserInfo(token, false);
    }

    public UserInfo extractUserInfo(String token, boolean isRefreshToken) {
        SecretKey key = isRefreshToken ? getRefreshSigningKey() : getSigningKey();
        Claims claims = extractAllClaims(token, key);
        String sub = claims.get("sub", String.class);
        List<String> roles = claims.get("roles", List.class);
        return UserInfo.builder()
                .id(Long.parseLong(sub))
                .roles(roles.stream().map(Role::valueOf).toList())
                .tenant(claims.get("tenant", String.class))
                .build();
    }

    public String generateAccessToken(UserInfo userInfo) {
        return generateAccessToken(userInfo.id().toString(), userInfo.roles(), userInfo.tenant());
    }

    public String generateRefreshToken(UserInfo userInfo) {
        return generateRefreshToken(userInfo.id().toString(), userInfo.roles(), userInfo.tenant());
    }

    public String generateAccessToken(String userId, List<Role> roles, String tenant) {
        return generateToken(userId, roles, tenant, false);
    }

    public String generateRefreshToken(String userId, List<Role> roles, String tenant) {
        return generateToken(userId, roles, tenant, true);
    }

    public String generateToken(String userId, List<Role> roles, String tenant, boolean isRefreshToken) {
        Date now = new Date();
        long expiration = isRefreshToken ? jwtProperties.getRefreshExpiration() : jwtProperties.getExpiration();
        Date expiryDate = new Date(now.getTime() + expiration);

        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);
        if (tenant != null) {
            claims.put("tenant", tenant);
        }

        SecretKey signingKey = isRefreshToken ? getRefreshSigningKey() : getSigningKey();
        return Jwts.builder()
                .subject(userId) // Use userId as subject
                .claims(claims)
                .issuedAt(now)
                .expiration(expiryDate)
                .signWith(signingKey)
                .compact();
    }

    /**
     * Generate a test token with default values
     */
    public String generateTestToken() {
        return generateAccessToken(
                "1",
                List.of(Role.ROLE_USER, Role.ROLE_ADMIN),
                "test-tenant"
        );
    }

    /**
     * Generate a test token with specific tenant
     */
    public String generateTestTokenWithTenant(String tenant) {
        return generateAccessToken(
                "1",
                List.of(Role.ROLE_USER, Role.ROLE_ADMIN),
                tenant
        );
    }

    /**
     * Generate an expired token for testing
     */
    public String generateExpiredToken() {
        Date now = new Date();
        Date pastDate = new Date(now.getTime() - 3600000); // 1 hour ago

        return Jwts.builder()
                .subject("1") // Use userId as subject
                .claims(Map.of(
                        "userId", "1",
                        "roles", List.of(Role.ROLE_USER, Role.ROLE_ADMIN),
                        "tenant", "test-tenant"
                ))
                .issuedAt(pastDate)
                .expiration(pastDate) // Already expired
                .signWith(getSigningKey())
                .compact();
    }
}
