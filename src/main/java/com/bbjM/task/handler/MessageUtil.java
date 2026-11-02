package com.bbjM.task.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageUtil {
    private final MessageSource messageSource;

    public String getMessage(ErrorCode errorCode, Object... args) {
        return messageSource.getMessage(
                errorCode.getCode(), args, errorCode.getCode(), LocaleContextHolder.getLocale());
    }
}