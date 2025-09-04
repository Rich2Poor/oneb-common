package com.oneb.common.domain.community.dto;

import com.oneb.common.domain.community.enums.EventType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommunityEventDto {
    private EventType eventType; // e.g., "CREATE"

    private Long communityId;
    private String name;
    private String fullName;
    private String description;
    private String avatar;
    private boolean official;
    private String accessType; // PUBLIC, PRIVATE, etc.
    private Long memberCount;
    private List<String> tags;
    private Instant createdAt;

    private Long creatorId;
    private String creatorUsername;

    private Instant eventTimestamp;
}

