package com.oneb.common.domain.community;

import com.oneb.common.domain.community.dto.FollowEventDto;

public interface FollowKafkaHandler {
    void handle(FollowEventDto followEventDto);
}
