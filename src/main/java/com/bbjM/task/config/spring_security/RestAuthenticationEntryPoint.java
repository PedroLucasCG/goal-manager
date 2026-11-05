package com.bbjM.task.config.spring_security;

import com.bbjM.task.handler.ErrorApiResponse;
import com.bbjM.task.handler.ErrorCode;
import com.bbjM.task.handler.MessageUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.time.Instant;

@Component
@RequiredArgsConstructor
@Log4j2
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper mapper;
    private final MessageUtil messages;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("[start] RestAuthenticationEntryPoint - commence");
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        mapper.writeValue(response.getWriter(), ErrorApiResponse.builder()
                .code(ErrorCode.UNAUTHENTICATED_ON_LOGIN.getCode())
                .message(messages.getMessage(ErrorCode.UNAUTHENTICATED_ON_LOGIN))
                .timestamp(Instant.now())
                .build());
        log.debug("[finish] RestAuthenticationEntryPoint - commence");
    }
}
