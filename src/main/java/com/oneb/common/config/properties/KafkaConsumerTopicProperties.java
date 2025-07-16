package com.oneb.common.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(value = "app.common.kafka.consumer.topics")
public class KafkaConsumerTopicProperties {
    private List<String> userCreated;
    private List<String> userUpdated;
}
