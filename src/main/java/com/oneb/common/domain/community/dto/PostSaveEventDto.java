package com.oneb.common.domain.community.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(callSuper = true)
public class PostSaveEventDto extends PostActionEventDto {

    private Long saveId;
    private Long postAuthorId;
}
