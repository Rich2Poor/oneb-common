package com.oneb.common.util;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test class for JsonUtil.
 */
class JsonUtilTest {

    @Test
    void testSanitize_NullInput() {
        assertNull(JsonUtil.sanitize(null));
    }

    @Test
    void testSanitize_EmptyString() {
        assertEquals("null", JsonUtil.sanitize(""));
    }

    @Test
    void testSanitize_WhitespaceOnly() {
        assertEquals("null", JsonUtil.sanitize("   \t\n   "));
    }

    @Test
    void testSanitize_SimpleJsonObject() {
        String input = "{\"name\": \"John\", \"age\": 30}";
        String result = JsonUtil.sanitize(input);
        assertEquals("{\"name\": \"John\", \"age\": 30}", result);
    }

    @Test
    void testSanitize_SimpleJsonArray() {
        String input = "[1, 2, 3, 4]";
        String result = JsonUtil.sanitize(input);
        assertEquals("[1, 2, 3, 4]", result);
    }

    @Test
    void testSanitize_JsonWithMarkdownCodeBlocks() {
        String input = "```json\n{\"name\": \"John\", \"age\": 30}\n```";
        String result = JsonUtil.sanitize(input);
        assertEquals("{\"name\": \"John\", \"age\": 30}", result);
    }

    @Test
    void testSanitize_JsonWithMarkdownCodeBlocksNoLanguage() {
        String input = "```\n{\"name\": \"John\", \"age\": 30}\n```";
        String result = JsonUtil.sanitize(input);
        assertEquals("{\"name\": \"John\", \"age\": 30}", result);
    }

    @Test
    void testSanitize_JsonWithPrefixText() {
        String input = "Here is the JSON response: {\"status\": \"success\", \"data\": [1, 2, 3]}";
        String result = JsonUtil.sanitize(input);
        assertEquals("{\"status\": \"success\", \"data\": [1, 2, 3]}", result);
    }

    @Test
    void testSanitize_ArrayWithPrefixText() {
        String input = "The array is: [\"apple\", \"banana\", \"cherry\"]";
        String result = JsonUtil.sanitize(input);
        assertEquals("[\"apple\", \"banana\", \"cherry\"]", result);
    }

    @Test
    void testSanitize_JsonWithSuffixText() {
        String input = "{\"message\": \"Hello World\"} and some additional text here";
        String result = JsonUtil.sanitize(input);
        assertEquals("{\"message\": \"Hello World\"}", result);
    }

    @Test
    void testSanitize_NestedJsonObject() {
        String input = "{\"user\": {\"name\": \"John\", \"details\": {\"age\": 30, \"city\": \"NYC\"}}}";
        String result = JsonUtil.sanitize(input);
        assertEquals("{\"user\": {\"name\": \"John\", \"details\": {\"age\": 30, \"city\": \"NYC\"}}}", result);
    }

    @Test
    void testSanitize_NestedJsonArray() {
        String input = "[[1, 2], [3, 4], [5, [6, 7]]]";
        String result = JsonUtil.sanitize(input);
        assertEquals("[[1, 2], [3, 4], [5, [6, 7]]]", result);
    }

    @Test
    void testSanitize_JsonWithStringsContainingBraces() {
        String input = "{\"message\": \"This {is} a test with {braces}\", \"count\": 5}";
        String result = JsonUtil.sanitize(input);
        assertEquals("{\"message\": \"This {is} a test with {braces}\", \"count\": 5}", result);
    }

    @Test
    void testSanitize_JsonWithEscapedQuotes() {
        String input = "{\"message\": \"He said \\\"Hello\\\" to me\", \"valid\": true}";
        String result = JsonUtil.sanitize(input);
        assertEquals("{\"message\": \"He said \\\"Hello\\\" to me\", \"valid\": true}", result);
    }

    @Test
    void testSanitize_ArrayStartsFirst() {
        String input = "Some text [1, 2, 3] and then {\"key\": \"value\"}";
        String result = JsonUtil.sanitize(input);
        assertEquals("[1, 2, 3]", result);
    }

    @Test
    void testSanitize_ObjectStartsFirst() {
        String input = "Some text {\"key\": \"value\"} and then [1, 2, 3]";
        String result = JsonUtil.sanitize(input);
        assertEquals("{\"key\": \"value\"}", result);
    }

    @Test
    void testSanitize_InvalidJsonMissingClosingBrace() {
        String input = "{\"name\": \"John\", \"age\": 30";
        String result = JsonUtil.sanitize(input);
        // JsonSanitizer should handle this and potentially fix it
        assertNotNull(result);
        assertTrue(result.startsWith("{"));
    }

    @Test
    void testSanitize_InvalidJsonExtraClosingBrace() {
        String input = "{\"name\": \"John\", \"age\": 30}}";
        String result = JsonUtil.sanitize(input);
        assertEquals("{\"name\": \"John\", \"age\": 30}", result);
    }

    @Test
    void testSanitize_JsonWithWhitespaceAndNewlines() {
        String input = "  \n  {\n  \"name\": \"John\",\n  \"age\": 30\n}  \n  ";
        String result = JsonUtil.sanitize(input);
        assertEquals("{\n  \"name\": \"John\",\n  \"age\": 30\n}", result);
    }

    @Test
    void testSanitize_ComplexMarkdownWithMultipleCodeBlocks() {
        String input = "```json\n{\"first\": \"block\"}\n```\nSome text\n```\n{\"second\": \"block\"}\n```";
        String result = JsonUtil.sanitize(input);
        assertEquals("{\"first\": \"block\"}", result);
    }

    @Test
    void testSanitize_NoJsonFound() {
        String input = "This is just plain text with no JSON";
        String result = JsonUtil.sanitize(input);
        // JsonSanitizer tries to make it valid JSON by wrapping in quotes
        assertEquals("\"This\"", result);
    }

    @Test
    void testSanitize_OnlyOpeningBrace() {
        String input = "Just a { character";
        String result = JsonUtil.sanitize(input);
        // JsonSanitizer tries to fix it by making it a valid object
        assertEquals("{ \"character\":null}", result);
    }

    @Test
    void testSanitize_OnlyOpeningBracket() {
        String input = "Just a [ character";
        String result = JsonUtil.sanitize(input);
        // JsonSanitizer tries to fix it by making it a valid array
        assertEquals("[ \"character\"]", result);
    }

    @Test
    void testFindMatchingEnd_ValidObject() {
        String input = "{\"key\": \"value\"}";
        int result = JsonUtil.findMatchingEnd(input, false);
        assertEquals(15, result); // 0-based index of closing brace
    }

    @Test
    void testFindMatchingEnd_ValidArray() {
        String input = "[1, 2, 3]";
        int result = JsonUtil.findMatchingEnd(input, true);
        assertEquals(8, result);
    }

    @Test
    void testFindMatchingEnd_NestedObject() {
        String input = "{\"outer\": {\"inner\": \"value\"}}";
        int result = JsonUtil.findMatchingEnd(input, false);
        assertEquals(28, result); // 0-based index of closing brace
    }

    @Test
    void testFindMatchingEnd_NestedArray() {
        String input = "[[1, 2], [3, 4]]";
        int result = JsonUtil.findMatchingEnd(input, true);
        assertEquals(15, result);
    }

    @Test
    void testFindMatchingEnd_StringWithBraces() {
        String input = "{\"message\": \"This {has} braces\"}";
        int result = JsonUtil.findMatchingEnd(input, false);
        assertEquals(31, result); // 0-based index of closing brace
    }

    @Test
    void testFindMatchingEnd_StringWithEscapedQuotes() {
        String input = "{\"message\": \"He said \\\"Hello\\\"\"}";
        int result = JsonUtil.findMatchingEnd(input, false);
        assertEquals(31, result); // 0-based index of closing brace
    }

    @Test
    void testFindMatchingEnd_NullInput() {
        int result = JsonUtil.findMatchingEnd(null, false);
        assertEquals(-1, result);
    }

    @Test
    void testFindMatchingEnd_EmptyInput() {
        int result = JsonUtil.findMatchingEnd("", false);
        assertEquals(-1, result);
    }

    @Test
    void testFindMatchingEnd_NoMatchingEnd() {
        String input = "{\"key\": \"value\"";
        int result = JsonUtil.findMatchingEnd(input, false);
        assertEquals(-1, result);
    }

    @Test
    void testFindMatchingEnd_ArrayNoMatchingEnd() {
        String input = "[1, 2, 3";
        int result = JsonUtil.findMatchingEnd(input, true);
        assertEquals(-1, result);
    }
}
