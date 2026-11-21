package com.bbjM.task.goal.domain.requests.create_goal_request;

import com.bbjM.task.goal.domain.GoalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRepeatingGoalRequest implements CreateGoalBehaviorRequest{
    private Long repetitionCounter;
    private Long repetitionLimit;

    public GoalType getGoalType() {
        return GoalType.REPEATING;
    }
}
