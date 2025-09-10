package com.oneb.common.domain.community;

import com.oneb.common.domain.community.dto.CommentEventDto;

public interface CommentKafkaHandler {
    void handle(CommentEventDto postEventDto);
}
