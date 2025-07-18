package com.oneb.common.domain.user.model;

import com.oneb.common.domain.common.model.DateAudit;
import com.oneb.common.domain.user.enums.AuthProvider;
import com.oneb.common.domain.user.enums.UserStatus;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@MappedSuperclass
public class BaseUser extends DateAudit {

    protected String username;
    protected String firstName;
    protected String lastName;
    protected String avatar;
    protected String avatarLargeUrl;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    protected UserStatus status = UserStatus.ACTIVE;

    @NotNull
    @Enumerated(EnumType.STRING)
    protected AuthProvider provider;

    protected String providerId;

    @Column(name = "default_language")
    protected String defaultLanguage = "EN";

    @Column(name = "tenant", length = 20)
    protected String tenant;

    public boolean isBlocked() {
        return status == UserStatus.BLOCKED;
    }

    public boolean isActive() {
        return status == UserStatus.ACTIVE;
    }
}
