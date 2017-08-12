package com.tencent.aero.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NoPermissionException extends RuntimeException {

    public NoPermissionException() {

    }
    public NoPermissionException(String message) {
        super(message);
    }
}
