package com.oneb.common.annotation.resolver;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum HeaderKey {
    X_USER_ID("X-User-Id"),
    USER("User"),
    USER_INFO("User-Info"),
    X_USER_TENANT("X-User-Tenant"),
    X_USER_ROLES("X-User-Roles");

    private final String value;
}
