package com.oneb.common.domain.community;

import com.oneb.common.domain.community.dto.CommentReactionEventDto;

public interface CommentReactionKafkaHandler {
    void handle(CommentReactionEventDto commentReactionEventDto);
}
