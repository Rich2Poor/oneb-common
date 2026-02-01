package com.oneb.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerErrorException extends BaseException {

    private static final String ERROR_CODE = "INTERNAL_SERVER_ERROR";

    public InternalServerErrorException(String message) {
        super(ERROR_CODE, message);
    }

    public InternalServerErrorException(String errorCode, String message) {
        super(errorCode, message);
    }
}
