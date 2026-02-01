package com.oneb.common.domain.community.dto;

import com.oneb.common.domain.community.enums.EventType;
import com.oneb.common.domain.community.enums.ReactionType;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class CommentReactionEventDtoTest {

    @Test
    void testCommentReactionEventDto_Creation() {
        // Given
        CommentReactionEventDto event = new CommentReactionEventDto();
        event.setEventType(EventType.CREATE);
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
        event.setCommentId(101L);
        event.setCommentAuthorId(202L);

        // Then
        assertEquals(EventType.CREATE, event.getEventType());
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
        assertEquals(101L, event.getCommentId());
        assertEquals(202L, event.getCommentAuthorId());
    }

    @Test
    void testCommentReactionEventDto_AllArgsConstructor() {
        // Given
        Instant timestamp = Instant.now();
        CommentReactionEventDto event = new CommentReactionEventDto(789L, ReactionType.DISLIKE, 101L, 202L);
        event.setEventType(EventType.CREATE);
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
        assertEquals(101L, event.getCommentId());
        assertEquals(202L, event.getCommentAuthorId());
        assertEquals(EventType.CREATE, event.getEventType());
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
    void testCommentReactionEventDto_ToString() {
        // Given
        CommentReactionEventDto event = new CommentReactionEventDto(789L, ReactionType.LIKE, 101L, 202L);
        event.setEventType(EventType.CREATE);
        event.setAuthorId(1L);
        event.setPostId(123L);

        // When
        String toString = event.toString();

        // Then
        assertNotNull(toString);
        assertTrue(toString.contains("789"));
        assertTrue(toString.contains("LIKE"));
        assertTrue(toString.contains("101"));
        assertTrue(toString.contains("202"));
    }
}
