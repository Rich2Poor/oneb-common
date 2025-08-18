package com.oneb.common.ai;

import reactor.core.publisher.Mono;

/**
 * Interface for AI content generation services.
 * Provides methods to generate content from text prompts using various AI models.
 */
public interface AiContentGenerator {

    /**
     * Generates content from a prompt using the default model.
     *
     * @param prompt the input prompt for content generation
     * @return a Mono containing the generated content
     */
    Mono<String> generateContent(String prompt);

    /**
     * Generates content from a prompt using a specific model.
     *
     * @param prompt the input prompt for content generation
     * @param model the AI model to use for generation
     * @return a Mono containing the generated content
     */
    Mono<String> generateContent(String prompt, String model);

    /**
     * Generates content from a prompt with additional parameters.
     *
     * @param request the content generation request with all parameters
     * @return a Mono containing the generated content
     */
    Mono<String> generateContent(AiContentRequest request);

    /**
     * Checks if the AI service is available and healthy.
     *
     * @return a Mono containing true if the service is healthy, false otherwise
     */
    Mono<Boolean> isHealthy();
}
