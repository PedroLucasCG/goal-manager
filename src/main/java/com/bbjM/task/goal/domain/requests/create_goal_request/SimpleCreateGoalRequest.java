package com.bbjM.task.goal.domain.requests.create_goal_request;

import com.bbjM.task.goal.domain.GoalType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SimpleCreateGoalRequest implements CreateGoalBehaviorRequest {

    @Override
    @Schema(example = "SIMPLE")
    public GoalType getGoalType() {
        return GoalType.SIMPLE;
    }
}
