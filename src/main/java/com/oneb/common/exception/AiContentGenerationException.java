package com.oneb.common.exception;

/**
 * Exception thrown when AI content generation fails.
 */
public class AiContentGenerationException extends BaseException {

    private static final String ERROR_CODE = "AI_CONTENT_GENERATION_ERROR";

    public AiContentGenerationException(String message) {
        super(ERROR_CODE, message);
    }

    public AiContentGenerationException(String errorCode, String message) {
        super(errorCode, message);
    }
}
