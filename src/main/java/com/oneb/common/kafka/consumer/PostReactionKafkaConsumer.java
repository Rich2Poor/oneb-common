package com.oneb.common.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oneb.common.domain.community.PostReactionKafkaHandler;
import com.oneb.common.domain.community.dto.PostReactionEventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@ConditionalOnProperty(name = {"app.common.kafka.consumer.topics.postReactionEvents"})
@Component
@Slf4j
public class PostReactionKafkaConsumer {

    private final ObjectMapper objectMapper;
    private final PostReactionKafkaHandler postReactionKafkaHandler;

    @KafkaListener(topics = "#{@kafkaConsumerTopicProperties.postReactionEvents}")
    public void receivePostReactionEvent(String message) {
        try {
            PostReactionEventDto postReactionEventDto = objectMapper.readValue(message, PostReactionEventDto.class);
            postReactionKafkaHandler.handle(postReactionEventDto);

            log.info("Received post reaction event: {}", message);
        } catch (Exception e) {
            log.error("Error processing post reaction event {}", message, e);
        }
    }
}
