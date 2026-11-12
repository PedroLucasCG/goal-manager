package com.bbjM.task.goal.application.api.responses;

import com.bbjM.task.goal.domain.BaseGoal;
import com.bbjM.task.goal.domain.GoalType;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GoalResponse {
    private UUID id;
    private String title;
    private String description;
    private String color;
    private GoalType goalType;
    private UUID parentGoalId;
    private RecurringGoalResponse recurrenceDetails;
    private Long level;

    public GoalResponse(BaseGoal goal) {
        this.id = goal.getId();
        this.title = goal.getTitle();
        this.description = goal.getDescription();
        this.color = goal.getColor();
        this.parentGoalId = null;
        this.goalType = goal.getGoalType();
        this.level = goal.getLevel();
        if (Objects.nonNull(goal.getParentGoal())) {
            this.parentGoalId = goal.getParentGoal().getId();
        }
        this.recurrenceDetails = goal.getRecurringGoal() != null
                ? new RecurringGoalResponse(goal.getRecurringGoal())
                : null;
    }
}
