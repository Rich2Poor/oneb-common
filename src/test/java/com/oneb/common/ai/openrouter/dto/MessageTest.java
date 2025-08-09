package com.oneb.common.ai.openrouter.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MessageTest {

    @Test
    void system_shouldCreateSystemMessage() {
        // Given
        String content = "You are a helpful assistant";

        // When
        Message message = Message.system(content);

        // Then
        assertEquals("system", message.getRole());
        assertEquals(content, message.getContent());
    }

    @Test
    void user_shouldCreateUserMessage() {
        // Given
        String content = "Hello, how are you?";

        // When
        Message message = Message.user(content);

        // Then
        assertEquals("user", message.getRole());
        assertEquals(content, message.getContent());
    }

    @Test
    void assistant_shouldCreateAssistantMessage() {
        // Given
        String content = "I'm doing well, thank you!";

        // When
        Message message = Message.assistant(content);

        // Then
        assertEquals("assistant", message.getRole());
        assertEquals(content, message.getContent());
    }

    @Test
    void builder_shouldCreateMessageWithAllFields() {
        // Given & When
        Message message = Message.builder()
                .role("custom")
                .content("Custom content")
                .build();

        // Then
        assertEquals("custom", message.getRole());
        assertEquals("Custom content", message.getContent());
    }
}
