package com.bbjM.task.goal.domain.requests.patch_goal_request;

import com.bbjM.task.goal.domain.BaseGoal;
import com.bbjM.task.goal.domain.GoalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeadlinePatchGoalRequest implements PatchGoalBehaviorRequest{
    private Instant deadline;

    @Override
    public GoalType getGoalType() {
        return GoalType.REPEATING;
    }
}
