package com.bbjM.task.goal.application.api.requests;

import com.bbjM.task.goal.domain.Goal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateGoalRequest {
    private String title;
    private String description;
    private String color;
    private Goal parentGoal;
}
