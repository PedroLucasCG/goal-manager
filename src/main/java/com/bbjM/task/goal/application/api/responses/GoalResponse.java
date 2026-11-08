package com.bbjM.task.goal.application.api.responses;

import com.bbjM.task.goal.domain.Goal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GoalResponse {
    private UUID id;
    private String title;
    private String description;
    private String color;
    private UUID parentGoalId;

    public GoalResponse(Goal goal) {
        this.id = goal.getId();
        this.title = goal.getTitle();
        this.description = goal.getDescription();
        this.color = goal.getColor();
        this.parentGoalId = null;
        if (Objects.nonNull(goal.getParentGoal())) {
            this.parentGoalId = goal.getParentGoal().getId();
        }
    }
}
