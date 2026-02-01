package com.oneb.common.kafka.producer;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.oneb.common.util.JsonUtil;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public void send(String topic, Object payload, Map<String, String> headers) {
        send(topic, null, payload, headers);
    }

    public void send(String topic, String key, Object payload, Map<String, String> headers) {
        sendAsync(topic, key, payload, headers).join();
    }

    public CompletableFuture<SendResult<String, String>> sendAsync(String topic, String key, Object payload, Map<String, String> headers) {
        String value;
        if (payload instanceof String stringPayload) {
            value = stringPayload;
        } else {
            value = JsonUtil.toString(payload, objectMapper);
        }

        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, null, key, value, convertHeaders(headers));

        return kafkaTemplate.send(producerRecord).whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent message to topic: {}, key: {}, value: {}, headers: {}", topic, key, value, headers);
            } else {
                log.error("Failed to send message to topic: {}, key: {}, value: {}, headers: {}", topic, key, value, headers, ex);
            }
        });
    }

    private List<Header> convertHeaders(Map<String, String> headers) {
        if (headers == null || headers.isEmpty()) {
            return new ArrayList<>();
        }

        List<Header> kafkaHeaders = new ArrayList<>();
        headers.forEach((key, value) -> {
            if (value != null) {
                kafkaHeaders.add(new RecordHeader(key, value.getBytes(StandardCharsets.UTF_8)));
            }
        });
        return kafkaHeaders;
    }
}