package com.oneb.common.exception;

/**
 * Exception thrown when AI content generation fails.
 */
public class AiContentGenerationException extends RuntimeException {

    public AiContentGenerationException(String message) {
        super(message);
    }

    public AiContentGenerationException(String message, Throwable cause) {
        super(message, cause);
    }

    public AiContentGenerationException(Throwable cause) {
        super(cause);
    }
}
