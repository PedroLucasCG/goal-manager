package com.bbjM.task.handler;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    EMAIL_ALREADY_USED(HttpStatus.CONFLICT, "email.already.used"),
    CREDENTIAL_NOT_FOUND(HttpStatus.NOT_FOUND, "credential.not.found"),
    UNAUTHENTICATED_ON_LOGIN(HttpStatus.UNAUTHORIZED, "unauthorized.on.login"),
    ACCESS_DENIED(HttpStatus.FORBIDDEN, "access.denied"),
    USERNAME_ALREADY_USED(HttpStatus.CONFLICT, "username.already.used"),
    PARENT_GOAL_NOT_FOUND(HttpStatus.NOT_FOUND, "parent.goal.not.found" );

    private final HttpStatus status;
    private final String code;
}