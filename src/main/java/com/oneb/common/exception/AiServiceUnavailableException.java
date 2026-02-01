package com.oneb.common.exception;

/**
 * Exception thrown when the AI service is unavailable or unhealthy.
 */
public class AiServiceUnavailableException extends BaseException {

    private static final String ERROR_CODE = "AI_SERVICE_UNAVAILABLE";

    public AiServiceUnavailableException(String message) {
        super(ERROR_CODE, message);
    }
}
