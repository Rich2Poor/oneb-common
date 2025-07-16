package com.oneb.common.domain.user;

import com.oneb.common.domain.user.dto.CreateUserKafkaDto;
import com.oneb.common.domain.user.model.BaseUser;

public interface UserKafkaService {
    BaseUser createUserByKafka(CreateUserKafkaDto createUserKafkaDto);

    BaseUser updateUserByKafka(CreateUserKafkaDto updateUserKafkaDto);
}
