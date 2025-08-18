package com.oneb.common.domain.community;

import com.oneb.common.domain.community.dto.PostEventDto;

public interface PostKafkaHandler {
    void handle(PostEventDto postEventDto);
}
