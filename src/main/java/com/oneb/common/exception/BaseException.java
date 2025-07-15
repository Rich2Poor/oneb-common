package com.oneb.common.exception;

/**
 * Base exception class for all custom exceptions in OneB projects.
 * 
 * @author Tuyen
 * @version 1.0.0
 */
public abstract class BaseException extends RuntimeException {
    
    private final String errorCode;
    private final Object[] args;
    
    /**
     * Constructor with message.
     */
    protected BaseException(String message) {
        super(message);
        this.errorCode = null;
        this.args = null;
    }
    
    /**
     * Constructor with message and cause.
     */
    protected BaseException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = null;
        this.args = null;
    }
    
    /**
     * Constructor with error code and message.
     */
    protected BaseException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.args = null;
    }
    
    /**
     * Constructor with error code, message and cause.
     */
    protected BaseException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.args = null;
    }
    
    /**
     * Constructor with error code, message and arguments.
     */
    protected BaseException(String errorCode, String message, Object... args) {
        super(message);
        this.errorCode = errorCode;
        this.args = args;
    }
    
    /**
     * Constructor with error code, message, cause and arguments.
     */
    protected BaseException(String errorCode, String message, Throwable cause, Object... args) {
        super(message, cause);
        this.errorCode = errorCode;
        this.args = args;
    }
    
    /**
     * Get error code.
     */
    public String getErrorCode() {
        return errorCode;
    }
    
    /**
     * Get arguments.
     */
    public Object[] getArgs() {
        return args;
    }
    
    /**
     * Get formatted message with arguments.
     */
    public String getFormattedMessage() {
        if (args == null || args.length == 0) {
            return getMessage();
        }
        return String.format(getMessage(), args);
    }
}
