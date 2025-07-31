package com.oneb.common.domain.community;

import com.oneb.common.domain.community.dto.PostEventDto;

public interface PostKafkaService {
    void handle(PostEventDto postEventDto);
}
