package com.oneb.common.domain.message.enums;

/**
 * Defines the type of conversation.
 * Following industry best practices from WhatsApp, Telegram, Slack, Discord.
 * 
 * DIRECT: One-to-one conversation between two users
 * GROUP: Group conversation with multiple participants
 */
public enum ConversationType {
    /**
     * Direct one-to-one conversation between two users
     */
    DIRECT,
    
    /**
     * Group conversation with multiple participants (2+ users)
     */
    GROUP
}

