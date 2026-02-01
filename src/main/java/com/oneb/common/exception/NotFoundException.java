package com.oneb.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends BaseException {

    private static final String ERROR_CODE = "NOT_FOUND";

    public NotFoundException(String message) {
        super(ERROR_CODE, message);
    }

    public NotFoundException(String errorCode, String message) {
        super(errorCode, message);
    }

    public NotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(ERROR_CODE, String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    }

    public NotFoundException(String errorCode, String resourceName, String fieldName, Object fieldValue) {
        super(errorCode, String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue));
    }
}