package com.oneb.common.config;

import com.oneb.common.ai.AiContentGenerator;
import com.oneb.common.ai.openrouter.OpenRouterAiContentGenerator;
import com.oneb.common.config.properties.OpenRouterProperties;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Simple test for AI configuration components without Spring Boot context.
 */
class AiConfigurationSimpleTest {

    @Test
    void openRouterProperties_shouldHaveDefaultValues() {
        // Given & When
        OpenRouterProperties properties = new OpenRouterProperties();

        // Then
        assertFalse(properties.isEnabled());
        assertEquals("https://openrouter.ai/api/v1", properties.getBaseUrl());
        assertEquals("openai/gpt-3.5-turbo", properties.getDefaultModel());
        assertEquals(1.0, properties.getDefaultTemperature());
        assertEquals(1000, properties.getDefaultMaxTokens());
        assertEquals(3, properties.getMaxRetries());
    }

    @Test
    void openRouterProperties_shouldAllowCustomValues() {
        // Given
        OpenRouterProperties properties = new OpenRouterProperties();

        // When
        properties.setEnabled(true);
        properties.setApiKey("test-key");
        properties.setDefaultModel("gpt-4");
        properties.setDefaultTemperature(0.7);
        properties.setDefaultMaxTokens(500);

        // Then
        assertTrue(properties.isEnabled());
        assertEquals("test-key", properties.getApiKey());
        assertEquals("gpt-4", properties.getDefaultModel());
        assertEquals(0.7, properties.getDefaultTemperature());
        assertEquals(500, properties.getDefaultMaxTokens());
    }

    @Test
    void aiContentGenerator_shouldBeCreatable() {
        // Given
        OpenRouterProperties properties = new OpenRouterProperties();
        properties.setApiKey("test-key");
        properties.setBaseUrl("https://test.com");

        WebClient.Builder webClientBuilder = WebClient.builder();

        // When
        AiContentGenerator generator = new OpenRouterAiContentGenerator(webClientBuilder, properties);

        // Then
        assertNotNull(generator);
        assertTrue(generator instanceof OpenRouterAiContentGenerator);
    }
}
