package com.oneb.common.domain.community.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ThumbnailDTO {
    @NotNull
    private Long id;
    @NotNull
    private String url;
    @NotNull
    private Integer width;
    @NotNull
    private Integer height;
}
