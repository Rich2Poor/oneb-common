package com.oneb.common.domain.ws.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Enum for WebSocket message types
 * Provides type safety and prevents magic strings
 */
@Getter
@RequiredArgsConstructor
public enum OutgoingWsMessageType {
    NOTIFICATION(false),
    RECEIVED_MESSAGE(false),
    SENT_MESSAGE(false),
    SENT_MESSAGE_ERROR(false),
    MESSAGE_ACTION(true),
    VIDEO_ROOM_UPDATE(true);

    private final boolean needSubscribe;
}

