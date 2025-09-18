package com.oneb.common.domain.community;

import com.oneb.common.domain.community.dto.PostReactionEventDto;

public interface PostReactionKafkaHandler {
    void handle(PostReactionEventDto postReactionEventDto);
}
