package com.oneb.common.domain.common.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponseDTO<T> {
    public static final String DEFAULT_SUCCESS_CODE = "0";
    public static final String DEFAULT_SUCCESS_MESSAGE = "Success";

    @NotNull
    private String code;
    @NotNull
    private String message;
    private Integer totalElements;
    private List<T> content;

    public static <T> BaseResponseDTO<T> success() {
        return of(DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MESSAGE);
    }

    public static <T> BaseResponseDTO<T> of(String code, String message){
        BaseResponseDTO<T> response = new BaseResponseDTO<>();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    public static <T> BaseResponseDTO<T> of(List<T> content){
        BaseResponseDTO<T> response = success();
        response.setContent(content);
        response.setTotalElements(content.size());
       return response;
    }
}
