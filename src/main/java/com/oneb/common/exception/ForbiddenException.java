package com.oneb.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends BaseException {

    private static final String ERROR_CODE = "FORBIDDEN";

    public ForbiddenException() {
        super(ERROR_CODE, "You are not authorized to perform this action");
    }

    public ForbiddenException(String message) {
        super(ERROR_CODE, message);
    }

    public ForbiddenException(String errorCode, String message) {
        super(errorCode, message);
    }
}

