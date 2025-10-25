package com.oneb.common.domain.ws;

import com.oneb.common.domain.ws.dto.OutgoingWsMessageWrapper;
import com.oneb.common.domain.ws.enums.OutgoingWsMessageType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty(name = "app.common.redis.pub.ws-messages-channel")
public class WsMessageService {

    @Value("${app.common.redis.pub.ws-messages-channel}")
    private String wsMessagesChannel;

    private final RedisTemplate<String, Object> redisTemplate;

    public <T> void send(List<Long> recipientIds, OutgoingWsMessageType type, T data) {
        OutgoingWsMessageWrapper<T> outgoingWsMessageWrapper = OutgoingWsMessageWrapper
                .of(recipientIds, type, data);

        redisTemplate.convertAndSend(wsMessagesChannel, outgoingWsMessageWrapper);
    }

    public <T> void send(Long recipientId, OutgoingWsMessageType type, T data) {
        OutgoingWsMessageWrapper<T> outgoingWsMessageWrapper = OutgoingWsMessageWrapper
                .of(recipientId, type, data);

        redisTemplate.convertAndSend(wsMessagesChannel, outgoingWsMessageWrapper);
    }
}

