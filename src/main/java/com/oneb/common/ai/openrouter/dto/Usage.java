package com.oneb.common.ai.openrouter.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents usage statistics in the OpenRouter.ai chat completion response.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usage {

    /**
     * Number of tokens in the prompt.
     */
    @JsonProperty("prompt_tokens")
    private Integer promptTokens;

    /**
     * Number of tokens in the generated completion.
     */
    @JsonProperty("completion_tokens")
    private Integer completionTokens;

    /**
     * Total number of tokens used in the request (prompt + completion).
     */
    @JsonProperty("total_tokens")
    private Integer totalTokens;
}
