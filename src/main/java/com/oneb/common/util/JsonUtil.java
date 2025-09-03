package com.oneb.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.json.JsonSanitizer;

public class JsonUtil {

    public static String toString(Object payload, ObjectMapper objectMapper) {
        try {
            return objectMapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String sanitize(String raw) {
        if (raw == null) return null;
        String trimmed = raw.trim();
        trimmed = trimmed.replaceAll("(?s)```json\\s*", "").replaceAll("(?s)```", "").trim();
        int objStart = trimmed.indexOf('{');
        int arrStart = trimmed.indexOf('[');
        int start = -1;
        char startChar = 0;
        if (objStart >= 0 && (arrStart == -1 || objStart < arrStart)) { start = objStart; startChar = '{'; }
        else if (arrStart >= 0) { start = arrStart; startChar = '['; }
        if (start > 0) {
            trimmed = trimmed.substring(start);
        }
        if (!trimmed.isEmpty()) {
            int end = findMatchingEnd(trimmed, startChar == '[');
            if (end > 0 && end < trimmed.length()) {
                trimmed = trimmed.substring(0, end + 1);
            }
        }
        String sanitized = JsonSanitizer.sanitize(trimmed);
        return sanitized.trim();
    }

    public static int findMatchingEnd(String s, boolean isArray) {
        if (s == null || s.isEmpty()) return -1;
        char open = isArray ? '[' : '{';
        char close = isArray ? ']' : '}';
        int depth = 0;
        boolean inStr = false;
        boolean esc = false;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (inStr) {
                if (esc) { esc = false; }
                else if (c == '\\') { esc = true; }
                else if (c == '"') { inStr = false; }
                continue;
            }
            if (c == '"') { inStr = true; continue; }
            if (c == open) depth++;
            else if (c == close) {
                depth--;
                if (depth == 0) return i;
            }
        }
        return -1;
    }
}
