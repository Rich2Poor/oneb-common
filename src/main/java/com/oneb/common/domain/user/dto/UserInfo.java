package com.oneb.common.domain.user.dto;

import com.oneb.common.domain.user.enums.Role;
import lombok.Builder;

import java.util.List;

@Builder
public record UserInfo(Long id, String tenant, List<Role> roles) {
}
