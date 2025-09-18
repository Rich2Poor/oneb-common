package com.oneb.common.domain.community.dto;

import com.oneb.common.domain.community.enums.EventType;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class PostSaveEventDtoTest {

    @Test
    void testPostSaveEventDto_Creation() {
        // Given
        PostSaveEventDto event = new PostSaveEventDto();
        event.setEventType(EventType.SAVE);
        event.setAuthorId(1L);
        event.setAuthorUsername("testuser");
        event.setAuthorAvatar("avatar.jpg");
        event.setPostId(123L);
        event.setPostTitle("Test Post");
        event.setCommunityId(456L);
        event.setCommunityName("Test Community");
        event.setEventTimestamp(Instant.now());
        event.setSaveId(789L);
        event.setPostAuthorId(101L);

        // Then
        assertEquals(EventType.SAVE, event.getEventType());
        assertEquals(1L, event.getAuthorId());
        assertEquals("testuser", event.getAuthorUsername());
        assertEquals("avatar.jpg", event.getAuthorAvatar());
        assertEquals(123L, event.getPostId());
        assertEquals("Test Post", event.getPostTitle());
        assertEquals(456L, event.getCommunityId());
        assertEquals("Test Community", event.getCommunityName());
        assertNotNull(event.getEventTimestamp());
        assertEquals(789L, event.getSaveId());
        assertEquals(101L, event.getPostAuthorId());
    }

    @Test
    void testPostSaveEventDto_AllArgsConstructor() {
        // Given
        Instant timestamp = Instant.now();
        PostSaveEventDto event = new PostSaveEventDto(789L, 101L);
        event.setEventType(EventType.SAVE);
        event.setAuthorId(1L);
        event.setAuthorUsername("testuser");
        event.setAuthorAvatar("avatar.jpg");
        event.setPostId(123L);
        event.setPostTitle("Test Post");
        event.setCommunityId(456L);
        event.setCommunityName("Test Community");
        event.setEventTimestamp(timestamp);

        // Then
        assertEquals(789L, event.getSaveId());
        assertEquals(101L, event.getPostAuthorId());
        assertEquals(EventType.SAVE, event.getEventType());
        assertEquals(1L, event.getAuthorId());
        assertEquals("testuser", event.getAuthorUsername());
        assertEquals("avatar.jpg", event.getAuthorAvatar());
        assertEquals(123L, event.getPostId());
        assertEquals("Test Post", event.getPostTitle());
        assertEquals(456L, event.getCommunityId());
        assertEquals("Test Community", event.getCommunityName());
        assertEquals(timestamp, event.getEventTimestamp());
    }

    @Test
    void testPostSaveEventDto_ToString() {
        // Given
        PostSaveEventDto event = new PostSaveEventDto(789L, 101L);
        event.setEventType(EventType.SAVE);
        event.setAuthorId(1L);
        event.setPostId(123L);

        // When
        String toString = event.toString();

        // Then
        assertNotNull(toString);
        assertTrue(toString.contains("789"));
        assertTrue(toString.contains("101"));
    }
}
