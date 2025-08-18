package com.oneb.common.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oneb.common.domain.user.UserKafkaService;
import com.oneb.common.domain.user.dto.UpdateUserKafkaDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@ConditionalOnProperty(name = {"app.common.kafka.consumer.topics.userUpdated"})
@Component
@Slf4j
public class UserUpdateKafkaConsumer {

    private final ObjectMapper objectMapper;
    private final UserKafkaService userKafkaService;

    @KafkaListener(topics = "#{@kafkaConsumerTopicProperties.userUpdated}")
    public void receiveCommunityUpdateUser(String updateUserDtoJson) {
        try {
            UpdateUserKafkaDto updateUserKafkaDto = objectMapper.readValue(updateUserDtoJson, UpdateUserKafkaDto.class);
            userKafkaService.updateUserByKafka(updateUserKafkaDto);

            log.info("Received update user: {}", updateUserDtoJson);
        } catch (Exception e) {
            log.error("Error processing update user event", e);
        }
    }
}

