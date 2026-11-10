package com.bbjM.task.goal.application.api.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRecurringGoalRequest extends CreateGoalRequest{
    @NotNull(message = "A duração é obrigatória para objetivos recorrentes")
    private Duration duration;
}
