package com.bbjM.task.goal.application.api.responses;

import com.bbjM.task.goal.domain.RecurringGoal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RecurringGoalResponse extends GoalResponse {
    private Duration interval;
    private LocalDateTime timeToReschedule;

    public RecurringGoalResponse(RecurringGoal recurringGoal) {
        this.interval = recurringGoal.getInterval();
        this.timeToReschedule = recurringGoal.getRecurrenceMoment();
    }
}
