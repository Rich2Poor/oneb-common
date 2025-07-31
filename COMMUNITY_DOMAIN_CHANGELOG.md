# Community Domain Changelog

## Version 1.0.8-SNAPSHOT

### Added Community Domain Support

#### New Packages
- `com.oneb.common.domain.community.dto` - Community-related DTOs
- `com.oneb.common.domain.community.enums` - Community-related enums
- `com.oneb.common.domain.community.model` - Community-related models

#### New Classes

##### Enums
1. **PostEventType** (`com.oneb.common.domain.community.enums.PostEventType`)
   - `CREATE` - Post creation event
   - `DELETE` - Post deletion event

2. **PostStatus** (`com.oneb.common.domain.community.enums.PostStatus`)
   - `DRAFT` - Draft post
   - `PUBLISHED` - Published post
   - `ARCHIVED` - Archived post
   - `DELETED` - Deleted post
   - `PENDING_APPROVAL` - Pending approval
   - `REJECTED` - Rejected post

3. **PostType** (`com.oneb.common.domain.community.enums.PostType`)
   - `TEXT` - Text post
   - `LINK` - Link post
   - `IMAGE` - Image post
   - `VIDEO` - Video post
   - `POLL` - Poll post

4. **MediaType** (`com.oneb.common.domain.community.enums.MediaType`)
   - `IMAGE` - Image mediaDTO
   - `VIDEO` - Video mediaDTO
   - `AUDIO` - Audio mediaDTO
   - `DOCUMENT` - Document mediaDTO

##### Models
1. **Media** (`com.oneb.common.domain.community.model.MediaDTO`)
   - `MediaType type` - Type of mediaDTO
   - `String url` - Media URL
   - `String name` - Media name
   - `Long size` - Media size in bytes
   - `String mimeType` - MIME type

##### DTOs
1. **PostEventDto** (`com.oneb.common.domain.community.dto.PostEventDto`)
   - `PostEventType eventType` - Type of event (CREATE/DELETE)
   - `Long postId` - Post ID
   - `String title` - Post title
   - `String content` - Post content (null for DELETE events)
   - `PostType type` - Post type (null for DELETE events)
   - `PostStatus status` - Post status
   - `Long authorId` - Author ID
   - `String authorUsername` - Author username
   - `Long communityId` - Community ID
   - `String communityName` - Community name
   - `String link` - Post link (null for DELETE events)
   - `List<Media> mediaGroup` - Media attachments (null for DELETE events)
   - `Instant createdAt` - Post creation time (null for DELETE events)
   - `Instant eventTimestamp` - Event timestamp

### Usage Example

```java
// Create a post event
PostEventDto createEvent = new PostEventDto();
createEvent.setEventType(PostEventType.CREATE);
createEvent.setPostId(1L);
createEvent.setTitle("New Post");
createEvent.setContent("Post content");
createEvent.setType(PostType.TEXT);
createEvent.setStatus(PostStatus.PUBLISHED);
createEvent.setAuthorId(100L);
createEvent.setAuthorUsername("user123");
createEvent.setCommunityId(200L);
createEvent.setCommunityName("Tech Community");
createEvent.setEventTimestamp(Instant.now());

// Create a delete event
PostEventDto deleteEvent = new PostEventDto();
deleteEvent.setEventType(PostEventType.DELETE);
deleteEvent.setPostId(1L);
deleteEvent.setTitle("Deleted Post");
deleteEvent.setStatus(PostStatus.DELETED);
deleteEvent.setAuthorId(100L);
deleteEvent.setAuthorUsername("user123");
deleteEvent.setCommunityId(200L);
deleteEvent.setCommunityName("Tech Community");
deleteEvent.setEventTimestamp(Instant.now());
```

### Migration Notes

Projects using these classes should:
1. Update imports from `com.langbat.community.*` to `com.oneb.common.domain.community.*`
2. Update dependency version to `1.0.8-SNAPSHOT` or later
3. Remove local copies of these classes from their projects

### Breaking Changes
None - this is a new addition to the common library.
