package oneb.com.common.exception;

import java.util.Map;
import java.util.HashMap;

/**
 * Exception for validation errors.
 * 
 * @author Tuyen
 * @version 1.0.0
 */
public class ValidationException extends BaseException {
    
    private final Map<String, String> fieldErrors;
    
    /**
     * Constructor with message.
     */
    public ValidationException(String message) {
        super("VALIDATION_ERROR", message);
        this.fieldErrors = new HashMap<>();
    }
    
    /**
     * Constructor with field errors.
     */
    public ValidationException(Map<String, String> fieldErrors) {
        super("VALIDATION_ERROR", "Validation failed");
        this.fieldErrors = fieldErrors != null ? new HashMap<>(fieldErrors) : new HashMap<>();
    }
    
    /**
     * Constructor with message and field errors.
     */
    public ValidationException(String message, Map<String, String> fieldErrors) {
        super("VALIDATION_ERROR", message);
        this.fieldErrors = fieldErrors != null ? new HashMap<>(fieldErrors) : new HashMap<>();
    }
    
    /**
     * Get field errors.
     */
    public Map<String, String> getFieldErrors() {
        return new HashMap<>(fieldErrors);
    }
    
    /**
     * Add field error.
     */
    public ValidationException addFieldError(String field, String error) {
        this.fieldErrors.put(field, error);
        return this;
    }
    
    /**
     * Check if has field errors.
     */
    public boolean hasFieldErrors() {
        return !fieldErrors.isEmpty();
    }
}
