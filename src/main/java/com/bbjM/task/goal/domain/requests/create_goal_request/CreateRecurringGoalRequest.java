package com.bbjM.task.goal.domain.requests.create_goal_request;

import com.bbjM.task.goal.domain.GoalType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRecurringGoalRequest implements CreateGoalBehaviorRequest{
    @NotNull(message = "A duração é obrigatória para objetivos recorrentes")
    private Duration duration;

    public GoalType getGoalType() {
        return GoalType.RECURRING;
    }
}
