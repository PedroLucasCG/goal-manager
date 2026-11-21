package com.bbjM.task.goal.domain.requests.create_goal_request;

import com.bbjM.task.goal.domain.GoalType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CreateSimpleGoalRequest implements CreateGoalBehaviorRequest{
    public GoalType getGoalType() {
        return GoalType.SIMPLE;
    }
}
