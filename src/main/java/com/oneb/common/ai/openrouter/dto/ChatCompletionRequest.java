package com.oneb.common.ai.openrouter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Request object for OpenRouter.ai chat completion API.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChatCompletionRequest {

    /**
     * ID of the model to use.
     */
    @JsonProperty("model")
    private String model;

    /**
     * A list of messages comprising the conversation so far.
     */
    @JsonProperty("messages")
    private List<Message> messages;

    /**
     * What sampling temperature to use, between 0 and 2.
     */
    @JsonProperty("temperature")
    private Double temperature;

    /**
     * The maximum number of tokens to generate in the chat completion.
     */
    @JsonProperty("max_tokens")
    private Integer maxTokens;

    /**
     * An alternative to sampling with temperature, called nucleus sampling.
     */
    @JsonProperty("top_p")
    private Double topP;

    /**
     * Number between -2.0 and 2.0. Positive values penalize new tokens based on their existing frequency.
     */
    @JsonProperty("frequency_penalty")
    private Double frequencyPenalty;

    /**
     * Number between -2.0 and 2.0. Positive values penalize new tokens based on whether they appear in the text so far.
     */
    @JsonProperty("presence_penalty")
    private Double presencePenalty;

    /**
     * Whether to return a stream of partial message deltas.
     */
    @JsonProperty("stream")
    @Builder.Default
    private Boolean stream = false;
}
