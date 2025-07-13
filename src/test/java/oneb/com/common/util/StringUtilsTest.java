package oneb.com.common.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for StringUtils.
 */
class StringUtilsTest {
    
    @Test
    void testIsEmpty() {
        assertTrue(StringUtils.isEmpty(null));
        assertTrue(StringUtils.isEmpty(""));
        assertFalse(StringUtils.isEmpty(" "));
        assertFalse(StringUtils.isEmpty("test"));
    }
    
    @Test
    void testIsNotEmpty() {
        assertFalse(StringUtils.isNotEmpty(null));
        assertFalse(StringUtils.isNotEmpty(""));
        assertTrue(StringUtils.isNotEmpty(" "));
        assertTrue(StringUtils.isNotEmpty("test"));
    }
    
    @Test
    void testIsBlank() {
        assertTrue(StringUtils.isBlank(null));
        assertTrue(StringUtils.isBlank(""));
        assertTrue(StringUtils.isBlank(" "));
        assertTrue(StringUtils.isBlank("  \t\n  "));
        assertFalse(StringUtils.isBlank("test"));
        assertFalse(StringUtils.isBlank(" test "));
    }
    
    @Test
    void testIsNotBlank() {
        assertFalse(StringUtils.isNotBlank(null));
        assertFalse(StringUtils.isNotBlank(""));
        assertFalse(StringUtils.isNotBlank(" "));
        assertFalse(StringUtils.isNotBlank("  \t\n  "));
        assertTrue(StringUtils.isNotBlank("test"));
        assertTrue(StringUtils.isNotBlank(" test "));
    }
    
    @Test
    void testDefaultIfEmpty() {
        assertEquals("default", StringUtils.defaultIfEmpty(null, "default"));
        assertEquals("default", StringUtils.defaultIfEmpty("", "default"));
        assertEquals(" ", StringUtils.defaultIfEmpty(" ", "default"));
        assertEquals("test", StringUtils.defaultIfEmpty("test", "default"));
    }
    
    @Test
    void testDefaultIfBlank() {
        assertEquals("default", StringUtils.defaultIfBlank(null, "default"));
        assertEquals("default", StringUtils.defaultIfBlank("", "default"));
        assertEquals("default", StringUtils.defaultIfBlank(" ", "default"));
        assertEquals("test", StringUtils.defaultIfBlank("test", "default"));
    }
    
    @Test
    void testCapitalize() {
        assertNull(StringUtils.capitalize(null));
        assertEquals("", StringUtils.capitalize(""));
        assertEquals("Test", StringUtils.capitalize("test"));
        assertEquals("Test", StringUtils.capitalize("Test"));
        assertEquals("TEST", StringUtils.capitalize("tEST"));
    }
    
    @Test
    void testToCamelCase() {
        assertNull(StringUtils.toCamelCase(null));
        assertEquals("", StringUtils.toCamelCase(""));
        assertEquals("testString", StringUtils.toCamelCase("test_string"));
        assertEquals("testString", StringUtils.toCamelCase("test-string"));
        assertEquals("testString", StringUtils.toCamelCase("test string"));
        assertEquals("testString", StringUtils.toCamelCase("TEST_STRING"));
    }
    
    @Test
    void testToSnakeCase() {
        assertNull(StringUtils.toSnakeCase(null));
        assertEquals("", StringUtils.toSnakeCase(""));
        assertEquals("test_string", StringUtils.toSnakeCase("testString"));
        assertEquals("test_string", StringUtils.toSnakeCase("TestString"));
        assertEquals("test_string", StringUtils.toSnakeCase("test-string"));
        assertEquals("test_string", StringUtils.toSnakeCase("test string"));
    }
    
    @Test
    void testIsValidEmail() {
        assertFalse(StringUtils.isValidEmail(null));
        assertFalse(StringUtils.isValidEmail(""));
        assertFalse(StringUtils.isValidEmail("invalid"));
        assertFalse(StringUtils.isValidEmail("invalid@"));
        assertFalse(StringUtils.isValidEmail("@invalid.com"));
        assertTrue(StringUtils.isValidEmail("test@example.com"));
        assertTrue(StringUtils.isValidEmail("user.name+tag@example.co.uk"));
    }
    
    @Test
    void testIsValidPhone() {
        assertFalse(StringUtils.isValidPhone(null));
        assertFalse(StringUtils.isValidPhone(""));
        assertFalse(StringUtils.isValidPhone("123"));
        assertFalse(StringUtils.isValidPhone("abc1234567890"));
        assertTrue(StringUtils.isValidPhone("1234567890"));
        assertTrue(StringUtils.isValidPhone("+1234567890"));
        assertTrue(StringUtils.isValidPhone("123-456-7890"));
        assertTrue(StringUtils.isValidPhone("(123) 456-7890"));
    }
    
    @Test
    void testTruncate() {
        assertNull(StringUtils.truncate(null, 10));
        assertEquals("", StringUtils.truncate("", 10));
        assertEquals("test", StringUtils.truncate("test", 10));
        assertEquals("test", StringUtils.truncate("test", 4));
        assertEquals("t...", StringUtils.truncate("test", 3));
        assertEquals("te...", StringUtils.truncate("testing", 5));
    }
    
    @Test
    void testRemoveWhitespace() {
        assertNull(StringUtils.removeWhitespace(null));
        assertEquals("", StringUtils.removeWhitespace(""));
        assertEquals("test", StringUtils.removeWhitespace("test"));
        assertEquals("test", StringUtils.removeWhitespace(" test "));
        assertEquals("teststring", StringUtils.removeWhitespace("test string"));
        assertEquals("teststring", StringUtils.removeWhitespace("test\t\nstring"));
    }
    
    @Test
    void testCountOccurrences() {
        assertEquals(0, StringUtils.countOccurrences(null, "test"));
        assertEquals(0, StringUtils.countOccurrences("test", null));
        assertEquals(0, StringUtils.countOccurrences("", "test"));
        assertEquals(0, StringUtils.countOccurrences("test", ""));
        assertEquals(2, StringUtils.countOccurrences("test test", "test"));
        assertEquals(4, StringUtils.countOccurrences("aaabbbaaaccc", "aa")); // overlapping: positions 0,1,6,7
    }
    
    @Test
    void testReverse() {
        assertNull(StringUtils.reverse(null));
        assertEquals("", StringUtils.reverse(""));
        assertEquals("tset", StringUtils.reverse("test"));
        assertEquals("gnirts tset", StringUtils.reverse("test string"));
    }
    
    @Test
    void testIsNumeric() {
        assertFalse(StringUtils.isNumeric(null));
        assertFalse(StringUtils.isNumeric(""));
        assertFalse(StringUtils.isNumeric("abc"));
        assertFalse(StringUtils.isNumeric("123abc"));
        assertFalse(StringUtils.isNumeric("12.34"));
        assertTrue(StringUtils.isNumeric("123"));
        assertTrue(StringUtils.isNumeric("0"));
    }
    
    @Test
    void testIsAlphabetic() {
        assertFalse(StringUtils.isAlphabetic(null));
        assertFalse(StringUtils.isAlphabetic(""));
        assertFalse(StringUtils.isAlphabetic("123"));
        assertFalse(StringUtils.isAlphabetic("abc123"));
        assertFalse(StringUtils.isAlphabetic("abc def"));
        assertTrue(StringUtils.isAlphabetic("abc"));
        assertTrue(StringUtils.isAlphabetic("ABC"));
        assertTrue(StringUtils.isAlphabetic("AbC"));
    }
    
    @Test
    void testIsAlphanumeric() {
        assertFalse(StringUtils.isAlphanumeric(null));
        assertFalse(StringUtils.isAlphanumeric(""));
        assertFalse(StringUtils.isAlphanumeric("abc def"));
        assertFalse(StringUtils.isAlphanumeric("abc-123"));
        assertTrue(StringUtils.isAlphanumeric("abc"));
        assertTrue(StringUtils.isAlphanumeric("123"));
        assertTrue(StringUtils.isAlphanumeric("abc123"));
        assertTrue(StringUtils.isAlphanumeric("ABC123"));
    }
}
