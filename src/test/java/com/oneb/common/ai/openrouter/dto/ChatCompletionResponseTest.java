package com.oneb.common.ai.openrouter.dto;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ChatCompletionResponseTest {

    @Test
    void getFirstChoiceContent_withValidChoices_shouldReturnContent() {
        // Given
        String expectedContent = "Generated content";
        ChatCompletionResponse response = ChatCompletionResponse.builder()
                .choices(List.of(
                        Choice.builder()
                                .message(Message.assistant(expectedContent))
                                .build()
                ))
                .build();

        // When
        String content = response.getFirstChoiceContent();

        // Then
        assertEquals(expectedContent, content);
    }

    @Test
    void getFirstChoiceContent_withEmptyChoices_shouldReturnNull() {
        // Given
        ChatCompletionResponse response = ChatCompletionResponse.builder()
                .choices(List.of())
                .build();

        // When
        String content = response.getFirstChoiceContent();

        // Then
        assertNull(content);
    }

    @Test
    void getFirstChoiceContent_withNullChoices_shouldReturnNull() {
        // Given
        ChatCompletionResponse response = ChatCompletionResponse.builder()
                .choices(null)
                .build();

        // When
        String content = response.getFirstChoiceContent();

        // Then
        assertNull(content);
    }

    @Test
    void getFirstChoiceContent_withNullMessage_shouldReturnNull() {
        // Given
        ChatCompletionResponse response = ChatCompletionResponse.builder()
                .choices(List.of(
                        Choice.builder()
                                .message(null)
                                .build()
                ))
                .build();

        // When
        String content = response.getFirstChoiceContent();

        // Then
        assertNull(content);
    }

    @Test
    void builder_shouldCreateCompleteResponse() {
        // Given & When
        ChatCompletionResponse response = ChatCompletionResponse.builder()
                .id("test-id")
                .object("chat.completion")
                .created(1234567890L)
                .model("gpt-4")
                .choices(List.of(
                        Choice.builder()
                                .index(0)
                                .message(Message.assistant("Test content"))
                                .finishReason("stop")
                                .build()
                ))
                .usage(Usage.builder()
                        .promptTokens(10)
                        .completionTokens(20)
                        .totalTokens(30)
                        .build())
                .build();

        // Then
        assertEquals("test-id", response.getId());
        assertEquals("chat.completion", response.getObject());
        assertEquals(1234567890L, response.getCreated());
        assertEquals("gpt-4", response.getModel());
        assertEquals(1, response.getChoices().size());
        assertEquals("Test content", response.getFirstChoiceContent());
        assertNotNull(response.getUsage());
        assertEquals(30, response.getUsage().getTotalTokens());
    }
}
