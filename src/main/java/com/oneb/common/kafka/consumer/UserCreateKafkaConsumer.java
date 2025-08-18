package com.oneb.common.kafka.consumer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oneb.common.domain.user.UserKafkaService;
import com.oneb.common.domain.user.dto.CreateUserKafkaDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@ConditionalOnProperty(name = {"app.common.kafka.consumer.topics.userCreated"})
@Component
@Slf4j
public class UserCreateKafkaConsumer {

    private final ObjectMapper objectMapper;
    private final UserKafkaService userKafkaService;

    @KafkaListener(topics = "#{@kafkaConsumerTopicProperties.userCreated}")
    public void receiveCommunityCreateUser(String createUserDtoJson) {
        try {
            CreateUserKafkaDto createUserKafkaDto = objectMapper.readValue(createUserDtoJson, CreateUserKafkaDto.class);
            userKafkaService.createUserByKafka(createUserKafkaDto);

            log.info("Received create user: {}", createUserDtoJson);
        } catch (Exception e) {
            log.error("Error processing create user event", e);
        }
    }
}

