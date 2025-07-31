package com.oneb.common.domain.community.dto;

import com.oneb.common.domain.community.enums.MediaType;
import com.oneb.common.domain.community.enums.PostEventType;
import com.oneb.common.domain.community.enums.PostStatus;
import com.oneb.common.domain.community.enums.PostType;
import com.oneb.common.domain.community.model.MediaDTO;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PostEventDtoTest {

    @Test
    void testPostEventDto_CreateEvent() {
        // Given
        MediaDTO mediaDTO = new MediaDTO();
        mediaDTO.setType(MediaType.IMAGE);
        mediaDTO.setUrl("test-image.jpg");

        PostEventDto event = new PostEventDto();
        event.setEventType(PostEventType.CREATE);
        event.setPostId(1L);
        event.setTitle("Test Post");
        event.setContent("Test Content");
        event.setType(PostType.TEXT);
        event.setStatus(PostStatus.PUBLISHED);
        event.setAuthorId(100L);
        event.setAuthorUsername("testuser");
        event.setCommunityId(200L);
        event.setCommunityName("testcommunity");
        event.setLink("test-post-link");
        event.setMediaGroup(List.of(mediaDTO));
        event.setCreatedAt(Instant.now());
        event.setEventTimestamp(Instant.now());

        // Then
        assertEquals(PostEventType.CREATE, event.getEventType());
        assertEquals(1L, event.getPostId());
        assertEquals("Test Post", event.getTitle());
        assertEquals("Test Content", event.getContent());
        assertEquals(PostType.TEXT, event.getType());
        assertEquals(PostStatus.PUBLISHED, event.getStatus());
        assertEquals(100L, event.getAuthorId());
        assertEquals("testuser", event.getAuthorUsername());
        assertEquals(200L, event.getCommunityId());
        assertEquals("testcommunity", event.getCommunityName());
        assertEquals("test-post-link", event.getLink());
        assertNotNull(event.getMediaGroup());
        assertEquals(1, event.getMediaGroup().size());
        assertEquals(MediaType.IMAGE, event.getMediaGroup().get(0).getType());
        assertNotNull(event.getCreatedAt());
        assertNotNull(event.getEventTimestamp());
    }

    @Test
    void testPostEventDto_DeleteEvent() {
        // Given
        PostEventDto deleteEvent = new PostEventDto();
        deleteEvent.setEventType(PostEventType.DELETE);
        deleteEvent.setPostId(1L);
        deleteEvent.setTitle("Test Post");
        deleteEvent.setStatus(PostStatus.DELETED);
        deleteEvent.setAuthorId(100L);
        deleteEvent.setAuthorUsername("testuser");
        deleteEvent.setCommunityId(200L);
        deleteEvent.setCommunityName("testcommunity");
        deleteEvent.setEventTimestamp(Instant.now());

        // Then
        assertEquals(PostEventType.DELETE, deleteEvent.getEventType());
        assertEquals(1L, deleteEvent.getPostId());
        assertEquals("Test Post", deleteEvent.getTitle());
        assertEquals(PostStatus.DELETED, deleteEvent.getStatus());
        assertEquals(100L, deleteEvent.getAuthorId());
        assertEquals("testuser", deleteEvent.getAuthorUsername());
        assertEquals(200L, deleteEvent.getCommunityId());
        assertEquals("testcommunity", deleteEvent.getCommunityName());
        assertNotNull(deleteEvent.getEventTimestamp());
        
        // These should be null for delete events
        assertNull(deleteEvent.getContent());
        assertNull(deleteEvent.getType());
        assertNull(deleteEvent.getLink());
        assertNull(deleteEvent.getMediaGroup());
        assertNull(deleteEvent.getCreatedAt());
    }
}
