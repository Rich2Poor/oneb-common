package com.oneb.common.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateUserKafkaDto {
    private Long id;
    private String username;
    private String fullName;
    private String avatar;
    private String avatarLargeUrl;
    private String bio;
}
