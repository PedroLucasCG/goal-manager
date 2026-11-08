package com.bbjM.task.goal.application.api.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GoalFilterRequest {
    @NotNull(message = "Não passe null para os parâmetros!")
    @Min(value = 0, message = "O nível de início deve ser no mínimo 0!")
    @Builder.Default
    private Long startLevel = 0L;

    @NotNull(message = "Não passe null para os parâmetros!")
    @Min(value = 1, message = "A profundidade de pesquisa deve ser no mínimo 1!")
    @Max(value = 5, message = "A profundidade de pesquisa deve ser no máximo 5!")
    @Builder.Default
    private Long depth = 3L;

    @NotNull(message = "Não passe null para os parâmetros!")
    @Min(value = 1, message = "O número de objetivos deve ser no mínimo 1!")
    @Max(value = 50, message = "O número de objetivos deve ser no máximo 50!")
    @Builder.Default
    private Long width = 10L;

    @NotNull(message = "Não passe null para os parâmetros!")
    @Min(value = 0, message = "O offset não pode ser negativo!")
    @Builder.Default
    private Long startOffset = 0L;
}
