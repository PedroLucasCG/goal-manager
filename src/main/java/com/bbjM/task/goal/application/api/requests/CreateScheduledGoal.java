package com.bbjM.task.goal.application.api.requests;

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
