package com.oneb.common.kafka.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.oneb.common.util.JsonUtil;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@ConditionalOnProperty(name = "app.common.kafka.enabled", havingValue = "true")
@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void send(String topic, Object payload) {
        send(topic, null, payload);
    }

    public void send(String topic, String key, Object payload) {
        sendAsync(topic, key, payload).join();
    }

    public CompletableFuture<SendResult<String, String>> sendAsync(String topic, String key, Object payload) {
        String value;
        if (payload instanceof String stringPayload) {
            value = stringPayload;
        } else {
            value = JsonUtil.toString(payload, objectMapper);
        }

        return kafkaTemplate.send(topic, key, value).whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent message to topic: {}, key: {}, value: {}", topic, key, value);
            } else {
                log.error("Failed to send message to topic: {}, key: {}, value: {}", topic, key, value, ex);
            }
        });
    }
}