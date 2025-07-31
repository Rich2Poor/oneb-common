package com.oneb.common.domain.community.model;

import com.oneb.common.domain.community.enums.MediaType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MediaDTO {
    @NotNull
    private MediaType type;
    @NotNull
    private Long id;
    @NotNull
    private String url;
    private String thumbnailUrl;
    private Long thumbnailId;
}
