package com.oneb.common.util;

import java.util.Collection;
import java.util.regex.Pattern;

/**
 * String utility methods for common operations.
 * 
 * @author Tuyen
 * @version 1.0.0
 */
public final class StringUtils {
    
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
        "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$"
    );
    
    private static final Pattern PHONE_PATTERN = Pattern.compile(
        "^[+]?[0-9]{10,15}$"
    );
    
    private StringUtils() {
        // Utility class
    }
    
    /**
     * Check if a string is null or empty.
     */
    public static boolean isEmpty(String str) {
        return str == null || str.isEmpty();
    }
    
    /**
     * Check if a string is not null and not empty.
     */
    public static boolean isNotEmpty(String str) {
        return !isEmpty(str);
    }
    
    /**
     * Check if a string is null, empty, or contains only whitespace.
     */
    public static boolean isBlank(String str) {
        return str == null || str.trim().isEmpty();
    }
    
    /**
     * Check if a string is not null, not empty, and contains non-whitespace characters.
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }
    
    /**
     * Return default value if string is null or empty.
     */
    public static String defaultIfEmpty(String str, String defaultValue) {
        return isEmpty(str) ? defaultValue : str;
    }
    
    /**
     * Return default value if string is null, empty, or blank.
     */
    public static String defaultIfBlank(String str, String defaultValue) {
        return isBlank(str) ? defaultValue : str;
    }
    
    /**
     * Capitalize first letter of a string.
     */
    public static String capitalize(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
    
    /**
     * Convert string to camelCase.
     */
    public static String toCamelCase(String str) {
        if (isEmpty(str)) {
            return str;
        }
        
        StringBuilder result = new StringBuilder();
        boolean capitalizeNext = false;
        
        for (char c : str.toCharArray()) {
            if (c == '_' || c == '-' || c == ' ') {
                capitalizeNext = true;
            } else if (capitalizeNext) {
                result.append(Character.toUpperCase(c));
                capitalizeNext = false;
            } else {
                result.append(Character.toLowerCase(c));
            }
        }
        
        return result.toString();
    }
    
    /**
     * Convert string to snake_case.
     */
    public static String toSnakeCase(String str) {
        if (isEmpty(str)) {
            return str;
        }
        
        return str.replaceAll("([a-z])([A-Z])", "$1_$2")
                  .replaceAll("[-\\s]", "_")
                  .toLowerCase();
    }
    
    /**
     * Join collection of strings with delimiter.
     */
    public static String join(Collection<String> collection, String delimiter) {
        if (collection == null || collection.isEmpty()) {
            return "";
        }
        return String.join(delimiter, collection);
    }
    
    /**
     * Join array of strings with delimiter.
     */
    public static String join(String[] array, String delimiter) {
        if (array == null || array.length == 0) {
            return "";
        }
        return String.join(delimiter, array);
    }
    
    /**
     * Validate email format.
     */
    public static boolean isValidEmail(String email) {
        return isNotEmpty(email) && EMAIL_PATTERN.matcher(email).matches();
    }
    
    /**
     * Validate phone number format.
     */
    public static boolean isValidPhone(String phone) {
        return isNotEmpty(phone) && PHONE_PATTERN.matcher(phone.replaceAll("[\\s-()]", "")).matches();
    }
    
    /**
     * Truncate string to specified length with ellipsis.
     */
    public static String truncate(String str, int maxLength) {
        if (isEmpty(str) || str.length() <= maxLength) {
            return str;
        }
        if (maxLength <= 3) {
            return str.substring(0, Math.max(1, maxLength - 3)) + "...";
        }
        return str.substring(0, maxLength - 3) + "...";
    }
    
    /**
     * Remove all whitespace from string.
     */
    public static String removeWhitespace(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return str.replaceAll("\\s", "");
    }
    
    /**
     * Count occurrences of substring in string.
     */
    public static int countOccurrences(String str, String substring) {
        if (isEmpty(str) || isEmpty(substring)) {
            return 0;
        }

        int count = 0;
        int index = 0;

        while ((index = str.indexOf(substring, index)) != -1) {
            count++;
            index += 1; // Move by 1 to allow overlapping matches
        }

        return count;
    }
    
    /**
     * Reverse a string.
     */
    public static String reverse(String str) {
        if (isEmpty(str)) {
            return str;
        }
        return new StringBuilder(str).reverse().toString();
    }
    
    /**
     * Check if string contains only digits.
     */
    public static boolean isNumeric(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return str.matches("\\d+");
    }
    
    /**
     * Check if string contains only alphabetic characters.
     */
    public static boolean isAlphabetic(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return str.matches("[a-zA-Z]+");
    }
    
    /**
     * Check if string contains only alphanumeric characters.
     */
    public static boolean isAlphanumeric(String str) {
        if (isEmpty(str)) {
            return false;
        }
        return str.matches("[a-zA-Z0-9]+");
    }
}
