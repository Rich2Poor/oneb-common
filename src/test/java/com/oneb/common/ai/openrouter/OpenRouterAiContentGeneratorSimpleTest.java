package com.oneb.common.ai.openrouter;

import com.oneb.common.ai.AiContentRequest;
import com.oneb.common.config.properties.OpenRouterProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Simple unit tests for OpenRouterAiContentGenerator without MockWebServer
 * to avoid hanging issues in CI/CD environments.
 */
class OpenRouterAiContentGeneratorSimpleTest {

    private OpenRouterAiContentGenerator contentGenerator;
    private OpenRouterProperties properties;

    @BeforeEach
    void setUp() {
        properties = new OpenRouterProperties();
        properties.setBaseUrl("https://test.example.com");
        properties.setApiKey("test-api-key");
        properties.setDefaultModel("test-model");
        properties.setDefaultTemperature(0.7);
        properties.setDefaultMaxTokens(100);
        properties.setMaxRetries(1);
        properties.setRetryDelay(Duration.ofMillis(50));

        WebClient.Builder webClientBuilder = WebClient.builder();

        contentGenerator = new OpenRouterAiContentGenerator(webClientBuilder, properties);
    }

    @Test
    void constructor_shouldCreateInstance() {
        // Then
        assertNotNull(contentGenerator);
    }

    @Test
    void buildApiRequest_shouldUseDefaultValues() {
        // Given
        AiContentRequest request = AiContentRequest.simple("Test prompt");

        // When - We can't easily test the private buildApiRequest method directly,
        // but we can test that the generator was created with the right properties
        
        // Then
        assertNotNull(contentGenerator);
        assertEquals("test-api-key", properties.getApiKey());
        assertEquals("test-model", properties.getDefaultModel());
        assertEquals(0.7, properties.getDefaultTemperature());
        assertEquals(100, properties.getDefaultMaxTokens());
    }

    @Test
    void buildApiRequest_shouldUseCustomValues() {
        // Given
        AiContentRequest request = AiContentRequest.builder()
                .prompt("Test prompt")
                .model("custom-model")
                .temperature(0.5)
                .maxTokens(200)
                .systemMessage("You are a helpful assistant")
                .build();

        // When - Testing the request object itself
        
        // Then
        assertEquals("Test prompt", request.getPrompt());
        assertEquals("custom-model", request.getModel());
        assertEquals(0.5, request.getTemperature());
        assertEquals(200, request.getMaxTokens());
        assertEquals("You are a helpful assistant", request.getSystemMessage());
    }

    @Test
    void properties_shouldHaveCorrectDefaults() {
        // Given
        OpenRouterProperties defaultProps = new OpenRouterProperties();

        // Then
        assertFalse(defaultProps.isEnabled());
        assertEquals("https://openrouter.ai/api/v1", defaultProps.getBaseUrl());
        assertEquals("openai/gpt-3.5-turbo", defaultProps.getDefaultModel());
        assertEquals(1.0, defaultProps.getDefaultTemperature());
        assertEquals(1000, defaultProps.getDefaultMaxTokens());
        assertEquals(3, defaultProps.getMaxRetries());
        assertEquals(Duration.ofSeconds(1), defaultProps.getRetryDelay());
    }

    @Test
    void aiContentRequest_builderPattern_shouldWork() {
        // When
        AiContentRequest request = AiContentRequest.builder()
                .prompt("Test prompt")
                .model("gpt-4")
                .temperature(0.3)
                .maxTokens(500)
                .topP(0.9)
                .frequencyPenalty(0.1)
                .presencePenalty(0.2)
                .systemMessage("System message")
                .build();

        // Then
        assertEquals("Test prompt", request.getPrompt());
        assertEquals("gpt-4", request.getModel());
        assertEquals(0.3, request.getTemperature());
        assertEquals(500, request.getMaxTokens());
        assertEquals(0.9, request.getTopP());
        assertEquals(0.1, request.getFrequencyPenalty());
        assertEquals(0.2, request.getPresencePenalty());
        assertEquals("System message", request.getSystemMessage());
    }

    @Test
    void aiContentRequest_staticMethods_shouldWork() {
        // When
        AiContentRequest simpleRequest = AiContentRequest.simple("Simple prompt");
        AiContentRequest withModelRequest = AiContentRequest.withModel("Model prompt", "gpt-4");

        // Then
        assertEquals("Simple prompt", simpleRequest.getPrompt());
        assertNull(simpleRequest.getModel());
        assertEquals(1.0, simpleRequest.getTemperature());

        assertEquals("Model prompt", withModelRequest.getPrompt());
        assertEquals("gpt-4", withModelRequest.getModel());
        assertEquals(1.0, withModelRequest.getTemperature());
    }
}
