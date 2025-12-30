package com.oneb.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Redis Configuration for Online User Tracking
 * <p>
 * Modern Spring Boot 3.x approach:
 * - Leverages auto-configuration as much as possible
 * - Only customizes RedisTemplate for JSON serialization needs
 * - Uses @ConditionalOnMissingBean to avoid conflicts
 * <p>
 * <b>Note:</b> This class uses Jackson 2 APIs (com.fasterxml.jackson).
 * Spring Boot 4.0 uses Jackson 3 by default. Projects using this library
 * should include spring-boot-jackson2 dependency if needed.
 */
@Slf4j
@Configuration
@ConditionalOnProperty(name = "spring.data.redis.host")
@RequiredArgsConstructor
public class CommonRedisConfig {

    private final ObjectMapper objectMapper;

    /**
     * Configure RedisTemplate with JSON serialization for complex objects
     * Only created if no other RedisTemplate bean exists
     */
    @Bean
    @ConditionalOnMissingBean(name = "redisTemplate")
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory connectionFactory) {
        log.info("Configuring custom RedisTemplate with JSON serialization");

        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // JSON serializer for values (using GenericJackson2JsonRedisSerializer as Jackson2JsonRedisSerializer is deprecated)
        GenericJackson2JsonRedisSerializer jsonSerializer =
                new GenericJackson2JsonRedisSerializer(objectMapper);

        // String serializer for keys (more efficient and readable)
        StringRedisSerializer stringSerializer = new StringRedisSerializer();

        // Configure serializers
        template.setKeySerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);
        template.setValueSerializer(jsonSerializer);
        template.setHashValueSerializer(jsonSerializer);

        // Enable default serialization for other types
        template.setDefaultSerializer(jsonSerializer);

        template.afterPropertiesSet();
        return template;
    }
}
