package com.oneb.common.ai.openrouter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents a message in the OpenRouter.ai chat completion API.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    /**
     * The role of the message author. One of "system", "user", or "assistant".
     */
    @JsonProperty("role")
    private String role;

    /**
     * The content of the message.
     */
    @JsonProperty("content")
    private String content;

    /**
     * Creates a system message.
     *
     * @param content the system message content
     * @return a new Message with role "system"
     */
    public static Message system(String content) {
        return Message.builder()
                .role("system")
                .content(content)
                .build();
    }

    /**
     * Creates a user message.
     *
     * @param content the user message content
     * @return a new Message with role "user"
     */
    public static Message user(String content) {
        return Message.builder()
                .role("user")
                .content(content)
                .build();
    }

    /**
     * Creates an assistant message.
     *
     * @param content the assistant message content
     * @return a new Message with role "assistant"
     */
    public static Message assistant(String content) {
        return Message.builder()
                .role("assistant")
                .content(content)
                .build();
    }
}
