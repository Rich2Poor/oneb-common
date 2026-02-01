package com.oneb.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ConflictException extends BaseException {

    private static final String ERROR_CODE = "CONFLICT";

    public ConflictException(String message) {
        super(ERROR_CODE, message);
    }

    public ConflictException(String errorCode, String message) {
        super(errorCode, message);
    }
}
