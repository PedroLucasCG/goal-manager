package com.bbjM.task.goal.domain.requests.create_goal_request;

import com.bbjM.task.goal.domain.GoalType;
import com.bbjM.task.goal.domain.requests.GoalBehaviorRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecurringGoalRequest implements GoalBehaviorRequest {
    @NotNull(message = "A duração é obrigatória para objetivos recorrentes")
    private Duration duration;

    @Override
    @Schema(example = "RECURRING")
    public GoalType getGoalType() {
        return GoalType.RECURRING;
    }
}
