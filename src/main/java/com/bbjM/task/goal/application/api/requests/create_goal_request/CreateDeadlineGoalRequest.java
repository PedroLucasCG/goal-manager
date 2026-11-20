package com.bbjM.task.goal.application.api.requests.create_goal_request;

import com.bbjM.task.goal.domain.GoalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateDeadlineGoalRequest implements CreateGoalBehaviorRequest{
    private Instant deadline;

    public GoalType getGoalType() {
        return GoalType.DEADLINE;
    }
}
