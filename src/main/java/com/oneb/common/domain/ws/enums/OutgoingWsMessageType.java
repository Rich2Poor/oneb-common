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
    NOTIFICATION,
    RECEIVED_MESSAGE,
    SENT_MESSAGE,
    SENT_MESSAGE_ERROR
}

