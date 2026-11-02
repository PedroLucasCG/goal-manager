package com.bbjM.task.handler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.Instant;
import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record ErrorApiResponse(
        String code, String message, List<FieldViolation> errors, Instant timestamp) {

    public record FieldViolation(String field, String message) {}
}
