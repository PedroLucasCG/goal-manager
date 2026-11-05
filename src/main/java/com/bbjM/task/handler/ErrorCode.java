package com.bbjM.task.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    EMAIL_ALREADY_USED(HttpStatus.CONFLICT, "email.already.used"),
    CREDENTIAL_NOT_FOUND(HttpStatus.NOT_FOUND, "credential.not.found"),
    USERNAME_ALREADY_USED(HttpStatus.CONFLICT, "username.already.used");

    private final HttpStatus status;
    private final String code;
}