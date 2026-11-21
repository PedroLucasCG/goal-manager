package com.bbjM.task.goal.domain.requests.patch_goal_request;

import com.bbjM.task.goal.domain.GoalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecurringPatchGoalRequest implements PatchGoalBehaviorRequest {
    private Duration interval;
    private LocalDateTime rescheduleTime;

    @Override
    public GoalType getGoalType() {
        return GoalType.RECURRING;
    }
}
