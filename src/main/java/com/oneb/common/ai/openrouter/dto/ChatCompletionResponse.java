package com.oneb.common.ai.openrouter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Response object for OpenRouter.ai chat completion API.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatCompletionResponse {

    /**
     * A unique identifier for the chat completion.
     */
    @JsonProperty("id")
    private String id;

    /**
     * The object type, which is always "chat.completion".
     */
    @JsonProperty("object")
    private String object;

    /**
     * The Unix timestamp (in seconds) of when the chat completion was created.
     */
    @JsonProperty("created")
    private Long created;

    /**
     * The model used for the chat completion.
     */
    @JsonProperty("model")
    private String model;

    /**
     * A list of chat completion choices.
     */
    @JsonProperty("choices")
    private List<Choice> choices;

    /**
     * Usage statistics for the completion request.
     */
    @JsonProperty("usage")
    private Usage usage;

    /**
     * Gets the content of the first choice, if available.
     *
     * @return the content of the first choice, or null if no choices are available
     */
    public String getFirstChoiceContent() {
        if (choices != null && !choices.isEmpty() && choices.get(0).getMessage() != null) {
            return choices.get(0).getMessage().getContent();
        }
        return null;
    }
}
