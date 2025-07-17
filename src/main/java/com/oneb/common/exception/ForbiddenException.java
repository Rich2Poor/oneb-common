package com.oneb.common.exception;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN) // Maps this exception to HTTP 403 Forbidden
public class ForbiddenException extends RuntimeException {

    public ForbiddenException() {
        super("You are not authorized to perform this action");
    }

    public ForbiddenException(String message) {
        super(message);
    }
}

