package com.oneb.common.domain.noti.dto;

import com.oneb.common.domain.noti.enums.NotificationType;
import com.oneb.common.domain.noti.enums.TargetType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * DTO for notification response to frontend
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationDto {

    @NotNull
    private Long id;
    @NotNull
    private NotificationType type;
    @NotNull
    private ActorInfoDto actorInfo;
    @NotNull
    private TargetType targetType;
    @NotNull
    private Long targetId;
    private String message;
    private Map<String, Object> payload;
    @NotNull
    private Boolean isRead;
    @NotNull
    private LocalDateTime displayAt;
}
