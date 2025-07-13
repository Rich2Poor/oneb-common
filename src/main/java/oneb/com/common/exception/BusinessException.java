package oneb.com.common.exception;

/**
 * Exception for business logic errors.
 * 
 * @author Tuyen
 * @version 1.0.0
 */
public class BusinessException extends BaseException {
    
    /**
     * Constructor with message.
     */
    public BusinessException(String message) {
        super("BUSINESS_ERROR", message);
    }
    
    /**
     * Constructor with message and cause.
     */
    public BusinessException(String message, Throwable cause) {
        super("BUSINESS_ERROR", message, cause);
    }
    
    /**
     * Constructor with error code and message.
     */
    public BusinessException(String errorCode, String message) {
        super(errorCode, message);
    }
    
    /**
     * Constructor with error code, message and cause.
     */
    public BusinessException(String errorCode, String message, Throwable cause) {
        super(errorCode, message, cause);
    }
    
    /**
     * Constructor with error code, message and arguments.
     */
    public BusinessException(String errorCode, String message, Object... args) {
        super(errorCode, message, args);
    }
    
    /**
     * Constructor with error code, message, cause and arguments.
     */
    public BusinessException(String errorCode, String message, Throwable cause, Object... args) {
        super(errorCode, message, cause, args);
    }
}
