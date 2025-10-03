package com.oneb.common.domain.noti.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO class for actor information in notifications
 * Used for transferring actor data to frontend
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ActorInfoDto {

    private Long actorId;

    @NotNull
    private String actorUsername;

    @NotNull
    private String actorAvatarUrl;

    @NotNull
    @Builder.Default
    private Integer actorCount = 1;

    @NotNull
    @Builder.Default
    private Boolean hasOverflow = false;
}
