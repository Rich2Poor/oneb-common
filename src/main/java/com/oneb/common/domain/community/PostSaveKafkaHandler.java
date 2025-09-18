package com.oneb.common.domain.community;

import com.oneb.common.domain.community.dto.PostSaveEventDto;

public interface PostSaveKafkaHandler {
    void handle(PostSaveEventDto postSaveEventDto);
}
