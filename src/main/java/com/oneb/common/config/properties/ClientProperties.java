package com.oneb.common.config.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Configuration properties for HTTP client.
 * Only loaded when client configuration is enabled.
 */
@Data
@Component
@ConditionalOnProperty(name = "app.common.client.enabled", havingValue = "true")
@ConfigurationProperties(value = "app.common.client")
public class ClientProperties {
    private Long timeout = 60000L;
    private Long readTimeout = 60000L;
    private Long writeTimeout = 60000L;
}
