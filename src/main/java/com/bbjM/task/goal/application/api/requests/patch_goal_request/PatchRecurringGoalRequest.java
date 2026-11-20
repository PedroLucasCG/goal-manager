package com.bbjM.task.goal.application.api.requests.patch_goal_request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Duration;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PatchRecurringGoalRequest extends PatchGoalRequest {
    private Duration interval;
    private LocalDateTime rescheduleTime;
}
