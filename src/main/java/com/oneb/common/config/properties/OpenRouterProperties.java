package com.oneb.common.config.properties;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.time.Duration;

/**
 * Configuration properties for OpenRouter.ai integration.
 * Only loaded when AI integration is enabled.
 */
@Data
@Component
@ConditionalOnProperty(name = "app.common.ai.openrouter.enabled", havingValue = "true")
@ConfigurationProperties(prefix = "app.common.ai.openrouter")
public class OpenRouterProperties {

    /**
     * Whether OpenRouter.ai integration is enabled.
     */
    private boolean enabled = false;

    /**
     * OpenRouter.ai API key for authentication.
     */
    private String apiKey;

    /**
     * Base URL for OpenRouter.ai API.
     */
    private String baseUrl = "https://openrouter.ai/api/v1";

    /**
     * Default model to use for content generation.
     */
    private String defaultModel = "openai/gpt-3.5-turbo";

    /**
     * Default temperature for content generation (0.0 to 2.0).
     */
    private Double defaultTemperature = 1.0;

    /**
     * Default maximum tokens for content generation.
     */
    private Integer defaultMaxTokens = 1000;

    /**
     * Default top-p value for nucleus sampling (0.0 to 1.0).
     */
    private Double defaultTopP = 1.0;

    /**
     * Default frequency penalty (-2.0 to 2.0).
     */
    private Double defaultFrequencyPenalty = 0.0;

    /**
     * Default presence penalty (-2.0 to 2.0).
     */
    private Double defaultPresencePenalty = 0.0;

    /**
     * HTTP client connection timeout.
     */
    private Duration connectionTimeout = Duration.ofSeconds(30);

    /**
     * HTTP client read timeout.
     */
    private Duration readTimeout = Duration.ofSeconds(60);

    /**
     * HTTP client write timeout.
     */
    private Duration writeTimeout = Duration.ofSeconds(60);

    /**
     * Maximum number of retry attempts for failed requests.
     */
    private int maxRetries = 3;

    /**
     * Delay between retry attempts.
     */
    private Duration retryDelay = Duration.ofSeconds(1);

    /**
     * Your application name for OpenRouter.ai tracking (optional).
     */
    private String appName;

    /**
     * Your application URL for OpenRouter.ai tracking (optional).
     */
    private String appUrl;
}
