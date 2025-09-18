package com.oneb.common.domain.community.dto;

import com.oneb.common.domain.community.enums.EventType;
import com.oneb.common.domain.community.enums.ReactionType;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class PostReactionEventDtoTest {

    @Test
    void testPostReactionEventDto_Creation() {
        // Given
        PostReactionEventDto event = new PostReactionEventDto();
        event.setEventType(EventType.REACTION);
        event.setAuthorId(1L);
        event.setAuthorUsername("testuser");
        event.setAuthorAvatar("avatar.jpg");
        event.setPostId(123L);
        event.setPostTitle("Test Post");
        event.setCommunityId(456L);
        event.setCommunityName("Test Community");
        event.setEventTimestamp(Instant.now());
        event.setReactionId(789L);
        event.setReactionType(ReactionType.LIKE);
        event.setPostAuthorId(101L);

        // Then
        assertEquals(EventType.REACTION, event.getEventType());
        assertEquals(1L, event.getAuthorId());
        assertEquals("testuser", event.getAuthorUsername());
        assertEquals("avatar.jpg", event.getAuthorAvatar());
        assertEquals(123L, event.getPostId());
        assertEquals("Test Post", event.getPostTitle());
        assertEquals(456L, event.getCommunityId());
        assertEquals("Test Community", event.getCommunityName());
        assertNotNull(event.getEventTimestamp());
        assertEquals(789L, event.getReactionId());
        assertEquals(ReactionType.LIKE, event.getReactionType());
        assertEquals(101L, event.getPostAuthorId());
    }

    @Test
    void testPostReactionEventDto_AllArgsConstructor() {
        // Given
        Instant timestamp = Instant.now();
        PostReactionEventDto event = new PostReactionEventDto(789L, ReactionType.DISLIKE, 101L);
        event.setEventType(EventType.REACTION);
        event.setAuthorId(1L);
        event.setAuthorUsername("testuser");
        event.setAuthorAvatar("avatar.jpg");
        event.setPostId(123L);
        event.setPostTitle("Test Post");
        event.setCommunityId(456L);
        event.setCommunityName("Test Community");
        event.setEventTimestamp(timestamp);

        // Then
        assertEquals(789L, event.getReactionId());
        assertEquals(ReactionType.DISLIKE, event.getReactionType());
        assertEquals(101L, event.getPostAuthorId());
        assertEquals(EventType.REACTION, event.getEventType());
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
    void testPostReactionEventDto_ToString() {
        // Given
        PostReactionEventDto event = new PostReactionEventDto(789L, ReactionType.LIKE, 101L);
        event.setEventType(EventType.REACTION);
        event.setAuthorId(1L);
        event.setPostId(123L);

        // When
        String toString = event.toString();

        // Then
        assertNotNull(toString);
        assertTrue(toString.contains("789"));
        assertTrue(toString.contains("LIKE"));
        assertTrue(toString.contains("101"));
    }
}
