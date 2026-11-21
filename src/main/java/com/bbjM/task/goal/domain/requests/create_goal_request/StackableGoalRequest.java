package com.bbjM.task.goal.domain.requests.create_goal_request;

import com.bbjM.task.goal.domain.GoalType;
import com.bbjM.task.goal.domain.requests.GoalBehaviorRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StackableGoalRequest implements GoalBehaviorRequest {
    private Long quantity;
    private Long stackLimit;

    @Override
    @Schema(example = "STACKABLE")
    public GoalType getGoalType() {
        return GoalType.STACKABLE;
    }
}
