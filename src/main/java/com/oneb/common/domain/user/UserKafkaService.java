package com.oneb.common.domain.user;

import com.oneb.common.domain.user.dto.CreateUserKafkaDto;
import com.oneb.common.domain.user.model.BaseUser;

public interface UserKafkaService<T extends BaseUser> {
    T createUserByKafka(CreateUserKafkaDto createUserKafkaDto);

    T updateUserByKafka(CreateUserKafkaDto updateUserKafkaDto);
}
