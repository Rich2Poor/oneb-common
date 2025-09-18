package com.oneb.common.domain.community.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.oneb.common.domain.community.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class PostActionEventDto {

    protected EventType eventType;
    protected Long authorId;
    protected String authorUsername;
    protected String authorAvatar;
    protected Long postId;
    protected String postTitle;
    protected Long communityId;
    protected String communityName;
    protected Instant eventTimestamp;
}
