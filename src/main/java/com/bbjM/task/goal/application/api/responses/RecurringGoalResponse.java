package com.bbjM.task.goal.application.api.responses;

import com.bbjM.task.goal.domain.BaseGoal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecurringGoalResponse extends GoalResponse {
    private Duration duration;

    public RecurringGoalResponse(BaseGoal goal) {
        this.duration = goal.getDuration();
    }
}
