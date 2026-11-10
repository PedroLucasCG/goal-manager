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
    PARENT_GOAL_NOT_FOUND(HttpStatus.NOT_FOUND, "parent.goal.not.found" ),
    GOAL_NOT_FOUND(HttpStatus.NOT_FOUND, "goal.not.found" ),
    GOAL_TYPE_MISMATCH(HttpStatus.BAD_REQUEST, "goal.type.mismatch" ),
    GOAL_NOT_RECURRING(HttpStatus.BAD_REQUEST, "goal.not.recurring" ),;

    private final HttpStatus status;
    private final String code;
}