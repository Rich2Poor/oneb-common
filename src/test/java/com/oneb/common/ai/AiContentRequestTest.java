package com.oneb.common.ai;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AiContentRequestTest {

    @Test
    void simple_shouldCreateRequestWithPromptOnly() {
        // Given
        String prompt = "Test prompt";

        // When
        AiContentRequest request = AiContentRequest.simple(prompt);

        // Then
        assertEquals(prompt, request.getPrompt());
        assertNull(request.getModel());
        assertEquals(1.0, request.getTemperature());
        assertEquals(1.0, request.getTopP());
        assertEquals(0.0, request.getFrequencyPenalty());
        assertEquals(0.0, request.getPresencePenalty());
        assertNull(request.getMaxTokens());
        assertNull(request.getSystemMessage());
    }

    @Test
    void withModel_shouldCreateRequestWithPromptAndModel() {
        // Given
        String prompt = "Test prompt";
        String model = "gpt-4";

        // When
        AiContentRequest request = AiContentRequest.withModel(prompt, model);

        // Then
        assertEquals(prompt, request.getPrompt());
        assertEquals(model, request.getModel());
        assertEquals(1.0, request.getTemperature());
    }

    @Test
    void builder_shouldCreateRequestWithAllParameters() {
        // Given & When
        AiContentRequest request = AiContentRequest.builder()
                .prompt("Test prompt")
                .model("gpt-4")
                .temperature(0.7)
                .maxTokens(500)
                .topP(0.9)
                .frequencyPenalty(0.1)
                .presencePenalty(0.2)
                .systemMessage("You are a helpful assistant")
                .build();

        // Then
        assertEquals("Test prompt", request.getPrompt());
        assertEquals("gpt-4", request.getModel());
        assertEquals(0.7, request.getTemperature());
        assertEquals(500, request.getMaxTokens());
        assertEquals(0.9, request.getTopP());
        assertEquals(0.1, request.getFrequencyPenalty());
        assertEquals(0.2, request.getPresencePenalty());
        assertEquals("You are a helpful assistant", request.getSystemMessage());
    }

    @Test
    void builder_withDefaults_shouldUseDefaultValues() {
        // Given & When
        AiContentRequest request = AiContentRequest.builder()
                .prompt("Test prompt")
                .build();

        // Then
        assertEquals("Test prompt", request.getPrompt());
        assertEquals(1.0, request.getTemperature());
        assertEquals(1.0, request.getTopP());
        assertEquals(0.0, request.getFrequencyPenalty());
        assertEquals(0.0, request.getPresencePenalty());
    }
}
