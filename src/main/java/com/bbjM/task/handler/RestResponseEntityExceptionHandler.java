package com.bbjM.task.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.UUID;

@RestControllerAdvice
@RequiredArgsConstructor
@Log4j2
public class RestResponseEntityExceptionHandler {
    private final MessageUtil messageUtil;

    @ExceptionHandler(APIException.class)
    public ResponseEntity<ErrorApiResponse> handleApi(APIException ex) {
        log.warn("Erro de negócio: {}", ex.getErrorCode());
        return ResponseEntity.status(ex.getStatus())
                .body(ErrorApiResponse.builder()
                        .code(ex.getErrorCode().getCode())
                        .message(messageUtil.getMessage(ex.getErrorCode(), ex.getArgs()))
                        .timestamp(Instant.now())
                        .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorApiResponse> handleValidation(MethodArgumentNotValidException ex) {
        var errors = ex.getBindingResult().getFieldErrors().stream()
                .map(f -> new ErrorApiResponse.FieldViolation(f.getField(), f.getDefaultMessage()))
                .toList();
        return ResponseEntity.badRequest()
                .body(ErrorApiResponse.builder()
                        .code("invalid.validation").message("Dados inválidos.")
                        .errors(errors).timestamp(Instant.now()).build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorApiResponse> handleUnexpected(Exception ex) {
        String errorId = UUID.randomUUID().toString();
        log.error("Erro inesperado [{}]", errorId, ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorApiResponse.builder()
                        .code("internal.error")
                        .message("Erro interno. Informe o código " + errorId + " ao suporte.")
                        .timestamp(Instant.now()).build());
    }
}
