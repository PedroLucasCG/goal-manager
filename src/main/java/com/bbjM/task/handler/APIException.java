package com.bbjM.task.handler;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class APIException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private final ErrorCode errorCode;
    private final transient Object[] args;

    public APIException(ErrorCode errorCode, Object... args) {
        super(errorCode.getCode());
        this.errorCode = errorCode;
        this.args = args;
    }

    public static APIException withCause(ErrorCode errorCode, Throwable cause, Object... args) {
        APIException ex = new APIException(errorCode, args);
        ex.initCause(cause);
        return ex;
    }

    public HttpStatus getStatus() {
        return errorCode.getStatus();
    }
}
