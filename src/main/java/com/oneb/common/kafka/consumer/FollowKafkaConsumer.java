package com.oneb.common.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oneb.common.domain.community.FollowKafkaHandler;
import com.oneb.common.domain.community.dto.FollowEventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@ConditionalOnProperty(name = {"app.common.kafka.consumer.topics.followEvents"})
@Component
@Slf4j
public class FollowKafkaConsumer {

    private final ObjectMapper objectMapper;
    private final FollowKafkaHandler followKafkaHandler;

    @KafkaListener(topics = "#{@kafkaConsumerTopicProperties.followEvents}")
    public void receiveFollowEvent(String message) {
        try {
            FollowEventDto followEventDto = objectMapper.readValue(message, FollowEventDto.class);
            followKafkaHandler.handle(followEventDto);

            log.info("Received follow event: {}", message);
        } catch (Exception e) {
            log.error("Error processing follow event {}", message, e);
        }
    }
}
