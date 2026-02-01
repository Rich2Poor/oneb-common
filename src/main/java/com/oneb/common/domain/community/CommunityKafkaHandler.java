package com.oneb.common.domain.community;

import com.oneb.common.domain.community.dto.CommunityEventDto;

public interface CommunityKafkaHandler {
    void handle(CommunityEventDto postEventDto);
}
