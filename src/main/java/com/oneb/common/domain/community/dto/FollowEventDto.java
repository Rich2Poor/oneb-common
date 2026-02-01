package com.oneb.common.domain.community.dto;

import com.oneb.common.domain.community.enums.EventType;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class FollowEventDto {
    private EventType eventType; // CREATE or DELETE
    private Long followId;
    private Long followerId;
    private String followerUsername;
    private String followerAvatar;
    private Long followedUserId;
    private String followedUsername;
    private String followedAvatar;
    private Instant eventTimestamp;
}
