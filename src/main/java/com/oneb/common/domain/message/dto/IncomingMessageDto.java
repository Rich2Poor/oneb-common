package com.oneb.common.domain.message.dto;

import com.oneb.common.domain.message.enums.ConversationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IncomingMessageDto {

    private String referenceId;
    private Long conversationId;
    private ConversationType conversationType;
    private String conversationName;
    private List<Long> participantIds;
    private Long senderId;
    private String content;
    private LocalDateTime createdAt;
}

