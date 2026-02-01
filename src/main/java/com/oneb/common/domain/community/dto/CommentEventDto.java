package com.oneb.common.domain.community.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.oneb.common.domain.community.model.MediaDTO;
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
public class CommentEventDto extends PostActionEventDto {
    private Long commentId;
    private String content;
    private Long parentId; // null if it's a root comment
    private String status; // CommentStatus as string
    private Long postAuthorId; // Author username of the post
    private Long parentAuthorId; // Username of parent comment author (null if root comment)
    private MediaDTO media; // JSON string of media attachments
    private Instant createdAt;
}
