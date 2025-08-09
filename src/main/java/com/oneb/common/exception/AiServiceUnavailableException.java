package com.oneb.common.exception;

/**
 * Exception thrown when the AI service is unavailable or unhealthy.
 */
public class AiServiceUnavailableException extends AiContentGenerationException {

    public AiServiceUnavailableException(String message) {
        super(message);
    }

    public AiServiceUnavailableException(String message, Throwable cause) {
        super(message, cause);
    }

    public AiServiceUnavailableException(Throwable cause) {
        super(cause);
    }
}
