package com.oneb.common.config;

import com.oneb.common.config.properties.RedisChannelProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;

/**
 * Auto-configuration for Redis Pub/Sub listeners.
 * <p>
 * Services configure channels and listener bean names in application.yml:
 * <pre>
 * app:
 *   common:
 *     redis:
 *       sub:
 *         enabled: true
 *         listeners:
 *           - channel: user-events
 *             listener: userEventListener
 *           - channel: notification-events
 *             listener: notificationListener
 * </pre>
 * <p>
 * Listener beans must implement Spring's {@link MessageListener} interface:
 * <pre>
 * {@code
 * @Component("userEventListener")
 * public class UserEventListener implements MessageListener {
 *     @Override
 *     public void onMessage(Message message, byte[] pattern) {
 *         String channel = new String(message.getChannel());
 *         String body = new String(message.getBody());
 *         // Process message
 *     }
 * }
 * }
 * </pre>
 */
@Slf4j
@Configuration
@ConditionalOnProperty(name = "app.common.redis.sub.enabled", havingValue = "true")
@RequiredArgsConstructor
public class RedisListenerConfig {

    private final ApplicationContext applicationContext;
    private final RedisChannelProperties channelProperties;
    private final RedisConnectionFactory connectionFactory;

    /**
     * Creates and configures the Redis message listener container.
     * <p>
     * Loads listener beans from Spring context based on configuration
     * and registers them to their respective channels.
     *
     * @return configured RedisMessageListenerContainer
     */
    @Bean
    public RedisMessageListenerContainer redisMessageListenerContainer() {
        log.info("Initializing Redis Pub/Sub listener container");

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);

        if (!channelProperties.hasListeners()) {
            log.warn("No Redis Pub/Sub listeners configured");
            return container;
        }

        registerListeners(container);

        log.info("Redis Pub/Sub listener container initialized successfully with {} listener(s)",
                channelProperties.getListeners().size());
        return container;
    }

    /**
     * Registers all configured listeners to their respective channels.
     */
    private void registerListeners(RedisMessageListenerContainer container) {
        for (RedisChannelProperties.ListenerConfig config : channelProperties.getListeners()) {
            if (!config.isValid()) {
                throw new IllegalArgumentException(
                        String.format("Invalid listener configuration: channel='%s', listener='%s'",
                                config.getChannel(), config.getListener()));
            }

            registerListener(container, config.getChannel(), config.getListener());
        }
    }

    /**
     * Registers a listener for a channel.
     */
    private void registerListener(RedisMessageListenerContainer container,
                                  String channelName,
                                  String listenerBeanName) {
        MessageListener listener = applicationContext.getBean(listenerBeanName, MessageListener.class);
        ChannelTopic topic = new ChannelTopic(channelName);

        container.addMessageListener(listener, topic);

        log.info("Registered listener '{}' for channel: {}", listenerBeanName, channelName);
    }
}

