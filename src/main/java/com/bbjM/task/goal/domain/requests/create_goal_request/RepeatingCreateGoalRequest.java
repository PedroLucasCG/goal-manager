package com.bbjM.task.goal.domain.requests.create_goal_request;

import com.bbjM.task.goal.domain.GoalType;
import com.bbjM.task.goal.domain.RepeatingGoal;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RepeatingCreateGoalRequest implements CreateGoalBehaviorRequest {
    private Long repetitionCounter;
    private Long repetitionLimit;

    public RepeatingCreateGoalRequest(RepeatingGoal repeatingGoal) {
        this.repetitionCounter = repeatingGoal.getRepetitionCounter();
        this.repetitionLimit = repeatingGoal.getRepetitionLimit();
    }

    @Override
    @Schema(example = "REPEATING")
    public GoalType getGoalType() {
        return GoalType.REPEATING;
    }
}
