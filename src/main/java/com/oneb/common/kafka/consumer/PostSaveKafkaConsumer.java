package com.oneb.common.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oneb.common.domain.community.PostSaveKafkaHandler;
import com.oneb.common.domain.community.dto.PostSaveEventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@ConditionalOnProperty(name = {"app.common.kafka.consumer.topics.postSaveEvents"})
@Component
@Slf4j
public class PostSaveKafkaConsumer {

    private final ObjectMapper objectMapper;
    private final PostSaveKafkaHandler postSaveKafkaHandler;

    @KafkaListener(topics = "#{@kafkaConsumerTopicProperties.postSaveEvents}")
    public void receivePostSaveEvent(String message) {
        try {
            PostSaveEventDto postSaveEventDto = objectMapper.readValue(message, PostSaveEventDto.class);
            postSaveKafkaHandler.handle(postSaveEventDto);

            log.info("Received post save event: {}", message);
        } catch (Exception e) {
            log.error("Error processing post save event {}", message, e);
        }
    }
}
