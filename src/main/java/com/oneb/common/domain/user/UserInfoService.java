package com.oneb.common.domain.user;

import com.oneb.common.domain.user.dto.UserInfo;
import com.oneb.common.domain.user.model.BaseUser;

import java.util.Optional;

public interface UserInfoService<T extends BaseUser> {
    Optional<T> findByUserInfo(UserInfo userInfo);
}
