package com.oneb.common.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Configuration properties for Redis Pub/Sub channels and their listeners.
 * <p>
 * Example configuration:
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
 *           - channel: order-events
 *             listener: orderEventListener
 * </pre>
 * <p>
 * Each listener entry specifies a channel and its corresponding listener bean name.
 * The lib will automatically:
 * 1. Load listener bean by name from Spring context
 * 2. Register it as message listener for the channel
 */
@Data
@Component
@ConfigurationProperties(prefix = "app.common.redis.sub")
public class RedisChannelProperties {

    /**
     * Enable/disable Redis Pub/Sub listener configuration
     */
    private boolean enabled = false;

    /**
     * List of channel-listener configurations
     */
    private List<ListenerConfig> listeners = new ArrayList<>();

    /**
     * Check if any listeners are configured
     */
    public boolean hasListeners() {
        return listeners != null && !listeners.isEmpty();
    }

    /**
     * Configuration for a single channel-listener pair
     */
    @Data
    public static class ListenerConfig {
        /**
         * Redis channel name
         */
        private String channel;

        /**
         * Spring bean name of the MessageListener implementation
         */
        private String listener;

        /**
         * Validate that both channel and listener are configured
         */
        public boolean isValid() {
            return channel != null && !channel.trim().isEmpty()
                    && listener != null && !listener.trim().isEmpty();
        }
    }
}

