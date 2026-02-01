package com.oneb.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends BaseException {

    private static final String ERROR_CODE = "BAD_REQUEST";

    public BadRequestException(String message) {
        super(ERROR_CODE, message);
    }

    public BadRequestException(String errorCode, String message) {
        super(errorCode, message);
    }
}
