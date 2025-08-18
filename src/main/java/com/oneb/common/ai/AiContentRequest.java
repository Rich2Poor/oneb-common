package com.oneb.common.ai;

import lombok.Builder;
import lombok.Data;

/**
 * Request object for AI content generation with configurable parameters.
 */
@Data
@Builder
public class AiContentRequest {

    /**
     * The input prompt for content generation.
     */
    private String prompt;

    /**
     * The AI model to use for generation (e.g., "gpt-4", "claude-3-opus").
     * If null, the default model will be used.
     */
    private String model;

    /**
     * Controls randomness in the output. Higher values make output more random.
     * Range: 0.0 to 2.0. Default: 1.0
     */
    @Builder.Default
    private Double temperature = 1.0;

    /**
     * Maximum number of tokens to generate in the response.
     * If null, the service default will be used.
     */
    private Integer maxTokens;

    /**
     * Controls diversity via nucleus sampling. 
     * Range: 0.0 to 1.0. Default: 1.0
     */
    @Builder.Default
    private Double topP = 1.0;

    /**
     * Number between -2.0 and 2.0. Positive values penalize new tokens based on their frequency.
     * Default: 0.0
     */
    @Builder.Default
    private Double frequencyPenalty = 0.0;

    /**
     * Number between -2.0 and 2.0. Positive values penalize new tokens based on whether they appear.
     * Default: 0.0
     */
    @Builder.Default
    private Double presencePenalty = 0.0;

    /**
     * System message to set the behavior of the assistant.
     */
    private String systemMessage;

    /**
     * Creates a simple request with just a prompt using default settings.
     *
     * @param prompt the input prompt
     * @return a new AiContentRequest
     */
    public static AiContentRequest simple(String prompt) {
        return AiContentRequest.builder()
                .prompt(prompt)
                .build();
    }

    /**
     * Creates a request with prompt and model.
     *
     * @param prompt the input prompt
     * @param model the AI model to use
     * @return a new AiContentRequest
     */
    public static AiContentRequest withModel(String prompt, String model) {
        return AiContentRequest.builder()
                .prompt(prompt)
                .model(model)
                .build();
    }
}
