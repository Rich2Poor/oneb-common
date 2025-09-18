package com.oneb.common.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oneb.common.domain.community.CommentReactionKafkaHandler;
import com.oneb.common.domain.community.dto.CommentReactionEventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@ConditionalOnProperty(name = {"app.common.kafka.consumer.topics.commentReactionEvents"})
@Component
@Slf4j
public class CommentReactionKafkaConsumer {

    private final ObjectMapper objectMapper;
    private final CommentReactionKafkaHandler commentReactionKafkaHandler;

    @KafkaListener(topics = "#{@kafkaConsumerTopicProperties.commentReactionEvents}")
    public void receiveCommentReactionEvent(String message) {
        try {
            CommentReactionEventDto commentReactionEventDto = objectMapper.readValue(message, CommentReactionEventDto.class);
            commentReactionKafkaHandler.handle(commentReactionEventDto);

            log.info("Received comment reaction event: {}", message);
        } catch (Exception e) {
            log.error("Error processing comment reaction event {}", message, e);
        }
    }
}
