package com.oneb.common.domain.ws.dto;

import com.oneb.common.domain.ws.enums.OutgoingWsMessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Generic wrapper for WebSocket messages
 * Provides a consistent structure for all WebSocket messages with metadata
 *
 * @param <T> The type of the message payload
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OutgoingWsMessage<T> {
    /**
     * Message type identifier
     */
    private OutgoingWsMessageType type;

    /**
     * The actual message payload
     */
    private T data;

    /**
     * Create a wrapper with type and data, auto-generating timestamp
     */
    public static <T> OutgoingWsMessage<T> of(OutgoingWsMessageType type, T data) {
        return OutgoingWsMessage.<T>builder()
                .type(type)
                .data(data)
                .build();
    }
}

