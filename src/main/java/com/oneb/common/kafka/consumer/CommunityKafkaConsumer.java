package com.oneb.common.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oneb.common.domain.community.CommunityKafkaHandler;
import com.oneb.common.domain.community.PostKafkaHandler;
import com.oneb.common.domain.community.dto.CommunityEventDto;
import com.oneb.common.domain.community.dto.PostEventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@ConditionalOnProperty(name = {"app.common.kafka.consumer.topics.communityEvents"})
@Component
@Slf4j
public class CommunityKafkaConsumer {

    private final ObjectMapper objectMapper;
    private final CommunityKafkaHandler communityKafkaHandler;

    @KafkaListener(topics = "#{@kafkaConsumerTopicProperties.communityEvents}")
    public void receiveCommunityEvent(String message) {
        try {
            CommunityEventDto communityEventDto = objectMapper.readValue(message, CommunityEventDto.class);
            communityKafkaHandler.handle(communityEventDto);

            log.info("Received community event: {}", message);
        } catch (Exception e) {
            log.error("Error processing community event {}", message, e);
        }
    }
}
