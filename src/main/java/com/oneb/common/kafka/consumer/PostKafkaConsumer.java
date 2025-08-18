package com.oneb.common.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oneb.common.domain.community.PostKafkaHandler;
import com.oneb.common.domain.community.dto.PostEventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@ConditionalOnProperty(name = {"app.common.kafka.consumer.topics.postEvents"})
@Component
@Slf4j
public class PostKafkaConsumer {

    private final ObjectMapper objectMapper;
    private final PostKafkaHandler postKafkaHandler;

    @KafkaListener(topics = "#{@kafkaConsumerTopicProperties.postEvents}")
    public void receiveCommunityCreateUser(String createUserDtoJson) {
        try {
            PostEventDto postEventDto = objectMapper.readValue(createUserDtoJson, PostEventDto.class);
            postKafkaHandler.handle(postEventDto);

            log.info("Received post event: {}", createUserDtoJson);
        } catch (Exception e) {
            log.error("Error processing post event", e);
        }
    }
}
