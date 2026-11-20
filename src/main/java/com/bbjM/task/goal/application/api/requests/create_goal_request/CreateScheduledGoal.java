package com.bbjM.task.goal.application.api.requests.create_goal_request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateScheduledGoal {
    private Instant momentToExecute;
}
