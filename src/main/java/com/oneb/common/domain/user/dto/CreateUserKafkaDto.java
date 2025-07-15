package com.oneb.common.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.oneb.common.domain.user.enums.AuthProvider;
import com.oneb.common.domain.user.enums.Role;
import com.oneb.common.domain.user.enums.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateUserKafkaDto {
    private Long id;
    private String username;
    private String lastName;
    private String firstName;
    private String avatar;
    protected String avatarLargeUrl;
    private String cover;
    private String coverLargeUrl;
    private String email;
    private String tel;
    private Instant confirmTelAt;
    private boolean emailVerified;
    private Instant nextIssue;
    private UserStatus status;
    private boolean emailVerifiedRequire;
    private Set<Role> roles;
    protected AuthProvider provider;
    private String providerId;
    private String tenant;
    private boolean clone;
}
