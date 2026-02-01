package com.oneb.common.domain.noti.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Message wrapper for Redis pub/sub notifications
 * Contains the notification data and target user ID
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NotificationMessage implements Serializable {

    /**
     * Target user ID to receive this notification
     */
    private Long userId;

    /**
     * The notification data
     */
    private NotificationDto notification;
}

