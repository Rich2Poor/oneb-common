package com.oneb.common.domain.community.dto;

import com.oneb.common.domain.community.enums.ReactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class CommentReactionEventDto extends PostActionEventDto {

    private Long reactionId;
    private ReactionType reactionType;
    private Long commentId;
    private Long commentAuthorId;
}
