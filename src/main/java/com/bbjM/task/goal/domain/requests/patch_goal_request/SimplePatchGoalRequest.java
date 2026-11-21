package com.bbjM.task.goal.domain.requests.patch_goal_request;

import com.bbjM.task.goal.domain.GoalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SimplePatchGoalRequest implements PatchGoalBehaviorRequest{
    @Override
    public GoalType getGoalType() {
        return GoalType.SIMPLE;
    }
}
