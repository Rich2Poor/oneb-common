package com.oneb.common.domain.common.dto;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Page;

import java.util.List;

@Setter
@Getter
@ToString
public class PagingDto<T> {
    @NotNull
    private int currentPage;
    @NotNull
    private int pageSize;
    @NotNull
    private long totalElements;
    @NotNull
    private int totalPages;
    @NotNull
    private int numberOfElements;

    @NotNull
    @Schema(description = "List of items")
    @ArraySchema(schema = @Schema(implementation = Object.class))
    private List<T> content;

    public PagingDto(Page<T> page) {
        this.currentPage = page.getNumber();
        this.pageSize = page.getSize();
        this.totalElements = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.content = page.getContent();
        this.numberOfElements = page.getNumberOfElements();
    }
}
