package com.oneb.common.config;

import com.oneb.common.config.properties.RedisChannelProperties;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for RedisChannelProperties.
 */
class RedisListenerConfigTest {

    private RedisChannelProperties channelProperties;

    @BeforeEach
    void setUp() {
        channelProperties = new RedisChannelProperties();
    }

    @Test
    void testChannelProperties_HasListeners() {
        // Given: Listeners configured
        List<RedisChannelProperties.ListenerConfig> listeners = new ArrayList<>();
        RedisChannelProperties.ListenerConfig config = new RedisChannelProperties.ListenerConfig();
        config.setChannel("channel1");
        config.setListener("listener1");
        listeners.add(config);
        channelProperties.setListeners(listeners);

        // Then: hasListeners returns true
        assertTrue(channelProperties.hasListeners());
    }

    @Test
    void testChannelProperties_NoListeners() {
        // Given: No listeners configured
        channelProperties.setListeners(new ArrayList<>());

        // Then: hasListeners returns false
        assertFalse(channelProperties.hasListeners());
    }

    @Test
    void testChannelProperties_GetListeners() {
        // Given: Multiple listeners configured
        List<RedisChannelProperties.ListenerConfig> listeners = new ArrayList<>();

        RedisChannelProperties.ListenerConfig config1 = new RedisChannelProperties.ListenerConfig();
        config1.setChannel("user-events");
        config1.setListener("userEventListener");
        listeners.add(config1);

        RedisChannelProperties.ListenerConfig config2 = new RedisChannelProperties.ListenerConfig();
        config2.setChannel("notification-events");
        config2.setListener("notificationListener");
        listeners.add(config2);

        channelProperties.setListeners(listeners);

        // When: Getting listeners
        List<RedisChannelProperties.ListenerConfig> result = channelProperties.getListeners();

        // Then: All listeners are returned
        assertEquals(2, result.size());
        assertEquals("user-events", result.get(0).getChannel());
        assertEquals("userEventListener", result.get(0).getListener());
        assertEquals("notification-events", result.get(1).getChannel());
        assertEquals("notificationListener", result.get(1).getListener());
    }

    @Test
    void testChannelProperties_GetListenerForChannel() {
        // Given: Listeners configured
        List<RedisChannelProperties.ListenerConfig> listeners = new ArrayList<>();
        RedisChannelProperties.ListenerConfig config = new RedisChannelProperties.ListenerConfig();
        config.setChannel("test-channel");
        config.setListener("testListener");
        listeners.add(config);
        channelProperties.setListeners(listeners);

        // When: Getting listener for channel
        RedisChannelProperties.ListenerConfig result = channelProperties.getListeners().get(0);

        // Then: Correct listener is returned
        assertEquals("test-channel", result.getChannel());
        assertEquals("testListener", result.getListener());
    }

    @Test
    void testListenerConfig_IsValid() {
        // Given: Valid listener config
        RedisChannelProperties.ListenerConfig config = new RedisChannelProperties.ListenerConfig();
        config.setChannel("test-channel");
        config.setListener("testListener");

        // Then: isValid returns true
        assertTrue(config.isValid());
    }

    @Test
    void testListenerConfig_IsInvalid_NullChannel() {
        // Given: Listener config with null channel
        RedisChannelProperties.ListenerConfig config = new RedisChannelProperties.ListenerConfig();
        config.setListener("testListener");

        // Then: isValid returns false
        assertFalse(config.isValid());
    }

    @Test
    void testListenerConfig_IsInvalid_EmptyChannel() {
        // Given: Listener config with empty channel
        RedisChannelProperties.ListenerConfig config = new RedisChannelProperties.ListenerConfig();
        config.setChannel("  ");
        config.setListener("testListener");

        // Then: isValid returns false
        assertFalse(config.isValid());
    }

    @Test
    void testListenerConfig_IsInvalid_NullListener() {
        // Given: Listener config with null listener
        RedisChannelProperties.ListenerConfig config = new RedisChannelProperties.ListenerConfig();
        config.setChannel("test-channel");

        // Then: isValid returns false
        assertFalse(config.isValid());
    }

    @Test
    void testListenerConfig_IsInvalid_EmptyListener() {
        // Given: Listener config with empty listener
        RedisChannelProperties.ListenerConfig config = new RedisChannelProperties.ListenerConfig();
        config.setChannel("test-channel");
        config.setListener("  ");

        // Then: isValid returns false
        assertFalse(config.isValid());
    }

    /**
     * Test listener implementation for testing purposes.
     */
    public static class TestListener implements MessageListener {
        private String lastMessage;
        private String lastChannel;

        @Override
        public void onMessage(Message message, byte[] pattern) {
            this.lastChannel = new String(message.getChannel());
            this.lastMessage = new String(message.getBody());
        }

        public String getLastMessage() {
            return lastMessage;
        }

        public String getLastChannel() {
            return lastChannel;
        }
    }
}

