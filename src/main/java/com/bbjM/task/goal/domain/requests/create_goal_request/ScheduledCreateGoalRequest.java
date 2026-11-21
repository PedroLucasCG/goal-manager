package com.bbjM.task.goal.domain.requests.create_goal_request;

import com.bbjM.task.goal.domain.GoalType;
import com.bbjM.task.goal.domain.ScheduledGoal;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduledCreateGoalRequest implements CreateGoalBehaviorRequest {
    private Instant momentToExecute;

    public ScheduledCreateGoalRequest(ScheduledGoal scheduledGoal) {
        this.momentToExecute = scheduledGoal.getMomentToExecute();
    }

    @Override
    @Schema(example = "SCHEDULED")
    public GoalType getGoalType() {
        return GoalType.SCHEDULED;
    }
}
