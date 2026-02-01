package com.oneb.common.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oneb.common.domain.community.CommentKafkaHandler;
import com.oneb.common.domain.community.CommunityKafkaHandler;
import com.oneb.common.domain.community.dto.CommentEventDto;
import com.oneb.common.domain.community.dto.CommunityEventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@ConditionalOnProperty(name = {"app.common.kafka.consumer.topics.commentEvents"})
@Component
@Slf4j
public class CommentKafkaConsumer {

    private final ObjectMapper objectMapper;
    private final CommentKafkaHandler commentKafkaHandler;

    @KafkaListener(topics = "#{@kafkaConsumerTopicProperties.commentEvents}")
    public void receiveCommentEvent(String message) {
        try {
            CommentEventDto commentEventDto = objectMapper.readValue(message, CommentEventDto.class);
            commentKafkaHandler.handle(commentEventDto);

            log.info("Received comment event: {}", message);
        } catch (Exception e) {
            log.error("Error processing comment event {}", message, e);
        }
    }
}
