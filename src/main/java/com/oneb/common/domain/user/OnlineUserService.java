package com.oneb.common.domain.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Service for managing online users in Redis
 */
@Service
@RequiredArgsConstructor
@Slf4j
@ConditionalOnProperty("app.online-users.online-users-key")
public class OnlineUserService {

    protected final RedisTemplate<String, Object> redisTemplate;

    @Value("${app.online-users.online-users-key}")
    private String onlineUserKey;

    /**
     * Check if user is online
     */
    public boolean isUserOnline(Long userId) {
        return redisTemplate.opsForHash().hasKey(onlineUserKey, userId.toString());
    }
}
