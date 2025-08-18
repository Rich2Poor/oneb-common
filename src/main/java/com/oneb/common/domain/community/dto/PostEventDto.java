package com.oneb.common.domain.community.dto;

import com.oneb.common.domain.community.enums.PostEventType;
import com.oneb.common.domain.community.enums.PostStatus;
import com.oneb.common.domain.community.enums.PostType;
import com.oneb.common.domain.community.model.MediaDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostEventDto {
    
    private PostEventType eventType;
    private Long postId;
    private String title;
    private String content;
    private PostType type;
    private PostStatus status;
    private Long authorId;
    private String authorUsername;
    private Long communityId;
    private String communityName;
    private String link;
    private List<MediaDTO> mediaGroup;
    private Instant createdAt;
    private Instant eventTimestamp;
}
