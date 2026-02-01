package com.oneb.common.exception;

import lombok.Getter;

/**
 * Exception thrown when AI service rate limits are exceeded.
 */
@Getter
public class AiRateLimitException extends BaseException {

    private static final String ERROR_CODE = "AI_RATE_LIMIT_ERROR";

    private final long retryAfterSeconds;

    public AiRateLimitException(String message, long retryAfterSeconds) {
        super(ERROR_CODE, message);
        this.retryAfterSeconds = retryAfterSeconds;
    }
}
