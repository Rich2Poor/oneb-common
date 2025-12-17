package com.oneb.common.domain.ws.dto;

import com.oneb.common.domain.ws.enums.OutgoingWsMessageType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
public class OutgoingWsMessageWrapper<T> {
    private List<Long> recipients;
    private boolean isBroadcast = false;
    private OutgoingWsMessage<T> message;

    public static <T> OutgoingWsMessageWrapper<T> of(List<Long> recipients, OutgoingWsMessage<T> message) {
        return OutgoingWsMessageWrapper.<T>builder()
                .recipients(recipients)
                .message(message)
                .build();
    }

    public static <T> OutgoingWsMessageWrapper<T> of(List<Long> recipients, OutgoingWsMessageType type, T data) {
        return of(recipients, OutgoingWsMessage.of(type, data));
    }

    public static <T> OutgoingWsMessageWrapper<T> of(Long recipient, OutgoingWsMessage<T> message) {
        return of(List.of(recipient), message);
    }

    public static <T> OutgoingWsMessageWrapper<T> of(Long recipient, OutgoingWsMessageType type, T data) {
        return of(List.of(recipient), type, data);
    }

    public static <T> OutgoingWsMessageWrapper<T> of(OutgoingWsMessageType type, T data) {
        return OutgoingWsMessageWrapper.<T>builder()
                .isBroadcast(true)
                .message(OutgoingWsMessage.of(type, data))
                .build();
    }
}

