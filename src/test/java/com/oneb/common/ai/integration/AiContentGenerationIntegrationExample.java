package com.oneb.common.ai.integration;

import com.oneb.common.ai.AiContentGenerator;
import com.oneb.common.ai.AiContentRequest;
import com.oneb.common.ai.openrouter.OpenRouterAiContentGenerator;
import com.oneb.common.config.properties.OpenRouterProperties;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;

/**
 * Integration example showing how to use the AI content generation library.
 * This test is disabled by default as it requires a real API key.
 * 
 * To run this test:
 * 1. Set your OpenRouter.ai API key as environment variable: OPENROUTER_API_KEY
 * 2. Remove the @Disabled annotation
 * 3. Run the test
 */
@Disabled("Requires real API key - enable manually for integration testing")
public class AiContentGenerationIntegrationExample {

    @Test
    void demonstrateBasicUsage() {
        // Setup (normally this would be done by Spring Boot auto-configuration)
        OpenRouterProperties properties = new OpenRouterProperties();
        properties.setApiKey(System.getenv("OPENROUTER_API_KEY")); // Set your API key
        properties.setBaseUrl("https://openrouter.ai/api/v1");
        properties.setDefaultModel("openai/gpt-3.5-turbo");
        properties.setDefaultTemperature(0.7);
        properties.setDefaultMaxTokens(100);

        WebClient.Builder webClientBuilder = WebClient.builder();

        AiContentGenerator aiContentGenerator = new OpenRouterAiContentGenerator(webClientBuilder, properties);

        // Test simple content generation
        Mono<String> result = aiContentGenerator.generateContent("Write a short greeting message");

        StepVerifier.create(result)
                .expectNextMatches(content -> content != null && !content.trim().isEmpty())
                .verifyComplete();
    }

    @Test
    void demonstrateAdvancedUsage() {
        // Setup
        OpenRouterProperties properties = new OpenRouterProperties();
        properties.setApiKey(System.getenv("OPENROUTER_API_KEY"));
        properties.setBaseUrl("https://openrouter.ai/api/v1");

        WebClient.Builder webClientBuilder = WebClient.builder();

        AiContentGenerator aiContentGenerator = new OpenRouterAiContentGenerator(webClientBuilder, properties);

        // Test advanced content generation with custom parameters
        AiContentRequest request = AiContentRequest.builder()
                .prompt("Explain the concept of reactive programming in one sentence")
                .model("openai/gpt-4")
                .temperature(0.3)
                .maxTokens(50)
                .systemMessage("You are a technical expert who explains complex concepts simply")
                .build();

        Mono<String> result = aiContentGenerator.generateContent(request);

        StepVerifier.create(result)
                .expectNextMatches(content -> {
                    System.out.println("Generated content: " + content);
                    return content != null && content.toLowerCase().contains("reactive");
                })
                .verifyComplete();
    }

    @Test
    void demonstrateHealthCheck() {
        // Setup
        OpenRouterProperties properties = new OpenRouterProperties();
        properties.setApiKey(System.getenv("OPENROUTER_API_KEY"));
        properties.setBaseUrl("https://openrouter.ai/api/v1");

        WebClient.Builder webClientBuilder = WebClient.builder();

        AiContentGenerator aiContentGenerator = new OpenRouterAiContentGenerator(webClientBuilder, properties);

        // Test health check
        Mono<Boolean> healthResult = aiContentGenerator.isHealthy();

        StepVerifier.create(healthResult)
                .expectNext(true)
                .verifyComplete();
    }

    @Test
    void demonstrateErrorHandling() {
        // Setup with invalid API key to demonstrate error handling
        OpenRouterProperties properties = new OpenRouterProperties();
        properties.setApiKey("invalid-api-key");
        properties.setBaseUrl("https://openrouter.ai/api/v1");
        properties.setMaxRetries(1);
        properties.setRetryDelay(Duration.ofMillis(100));

        WebClient.Builder webClientBuilder = WebClient.builder();

        AiContentGenerator aiContentGenerator = new OpenRouterAiContentGenerator(webClientBuilder, properties);

        // Test error handling
        Mono<String> result = aiContentGenerator.generateContent("Test prompt");

        StepVerifier.create(result)
                .expectError()
                .verify();
    }
}
