package oneb.com.common.util;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * JSON utility methods for serialization and deserialization.
 * This is a simple interface that can be implemented with different JSON libraries.
 * 
 * @author Tuyen
 * @version 1.0.0
 */
public final class JsonUtils {
    
    private static JsonProcessor processor;
    
    private JsonUtils() {
        // Utility class
    }
    
    /**
     * Set the JSON processor implementation.
     */
    public static void setProcessor(JsonProcessor processor) {
        JsonUtils.processor = processor;
    }
    
    /**
     * Convert object to JSON string.
     */
    public static String toJson(Object object) {
        if (processor == null) {
            throw new IllegalStateException("JsonProcessor not configured. Call JsonUtils.setProcessor() first.");
        }
        return processor.toJson(object);
    }
    
    /**
     * Convert JSON string to object of specified class.
     */
    public static <T> T fromJson(String json, Class<T> clazz) {
        if (processor == null) {
            throw new IllegalStateException("JsonProcessor not configured. Call JsonUtils.setProcessor() first.");
        }
        return processor.fromJson(json, clazz);
    }
    
    /**
     * Convert JSON string to object of specified type.
     */
    public static <T> T fromJson(String json, Type type) {
        if (processor == null) {
            throw new IllegalStateException("JsonProcessor not configured. Call JsonUtils.setProcessor() first.");
        }
        return processor.fromJson(json, type);
    }
    
    /**
     * Convert JSON string to List of specified type.
     */
    public static <T> List<T> fromJsonList(String json, Class<T> elementClass) {
        if (processor == null) {
            throw new IllegalStateException("JsonProcessor not configured. Call JsonUtils.setProcessor() first.");
        }
        return processor.fromJsonList(json, elementClass);
    }
    
    /**
     * Convert JSON string to Map.
     */
    public static Map<String, Object> fromJsonMap(String json) {
        if (processor == null) {
            throw new IllegalStateException("JsonProcessor not configured. Call JsonUtils.setProcessor() first.");
        }
        return processor.fromJsonMap(json);
    }
    
    /**
     * Check if string is valid JSON.
     */
    public static boolean isValidJson(String json) {
        if (processor == null) {
            throw new IllegalStateException("JsonProcessor not configured. Call JsonUtils.setProcessor() first.");
        }
        return processor.isValidJson(json);
    }
    
    /**
     * Pretty print JSON string.
     */
    public static String prettyPrint(String json) {
        if (processor == null) {
            throw new IllegalStateException("JsonProcessor not configured. Call JsonUtils.setProcessor() first.");
        }
        return processor.prettyPrint(json);
    }
    
    /**
     * Interface for JSON processing implementations.
     */
    public interface JsonProcessor {
        
        /**
         * Convert object to JSON string.
         */
        String toJson(Object object);
        
        /**
         * Convert JSON string to object of specified class.
         */
        <T> T fromJson(String json, Class<T> clazz);
        
        /**
         * Convert JSON string to object of specified type.
         */
        <T> T fromJson(String json, Type type);
        
        /**
         * Convert JSON string to List of specified type.
         */
        <T> List<T> fromJsonList(String json, Class<T> elementClass);
        
        /**
         * Convert JSON string to Map.
         */
        Map<String, Object> fromJsonMap(String json);
        
        /**
         * Check if string is valid JSON.
         */
        boolean isValidJson(String json);
        
        /**
         * Pretty print JSON string.
         */
        String prettyPrint(String json);
    }
}
