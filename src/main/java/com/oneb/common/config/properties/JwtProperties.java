package com.oneb.common.config.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration properties for JWT authentication
 */
@Data
@Component
@ConfigurationProperties(prefix = "jwt")
@ConditionalOnProperty(name = "jwt.secret")
public class JwtProperties {

    /**
     * JWT secret key for token validation
     */
    private String secret = "defaultSecretKeyThatShouldBeChangedInProduction";
    private String refreshSecret = "defaultSecretKeyThatShouldBeChangedInProduction";

    /**
     * JWT token expiration time in milliseconds
     */
    private long expiration = 86400000; // 24 hours
    private long refreshExpiration = 86400000; // 24 hours

    /**
     * Header name for JWT token
     */
    private String headerName = "Authorization";

    /**
     * Token prefix (e.g., "Bearer ")
     */
    private String tokenPrefix = "Bearer ";

    /**
     * Whether to pass user information to downstream services
     */
    private boolean passUserInfo = true;

    /**
     * Header names for passing user information to downstream services
     */
    private UserHeaders userHeaders = new UserHeaders();

    @Data
    public static class UserHeaders {
        private String userId = "X-User-Id";
        private String roles = "X-User-Roles";
        private String tenant = "X-User-Tenant";
    }
}
