package com.oneb.common.exception;

/**
 * Exception thrown when AI service rate limits are exceeded.
 */
public class AiRateLimitException extends AiContentGenerationException {

    private final long retryAfterSeconds;

    public AiRateLimitException(String message, long retryAfterSeconds) {
        super(message);
        this.retryAfterSeconds = retryAfterSeconds;
    }

    public AiRateLimitException(String message, Throwable cause, long retryAfterSeconds) {
        super(message, cause);
        this.retryAfterSeconds = retryAfterSeconds;
    }

    public long getRetryAfterSeconds() {
        return retryAfterSeconds;
    }
}
