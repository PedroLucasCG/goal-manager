package com.bbjM.task.goal.domain.responses;

import com.bbjM.task.goal.domain.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
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
    private List<GoalType> goalType;
    private UUID parentGoalId;
    private Status status;
    private Long level;
    private RecurringGoalResponse recurrenceDetails;
    private DeadlineGoalResponse deadlineDetails;
    private RepeatingGoalResponse repetitionDetails;
    private ScheduledGoalResponse scheduleDetails;
    private StackableGoalResponse stackDetails;

    public GoalResponse(BaseGoal goal) {
        this.id = goal.getId();
        this.title = goal.getTitle();
        this.description = goal.getDescription();
        this.color = goal.getColor();
        this.parentGoalId = null;
        this.goalType = goal.getGoalTypes();
        this.level = goal.getLevel();
        this.status = goal.getStatus();
        if (Objects.nonNull(goal.getParentGoal())) {
            this.parentGoalId = goal.getParentGoal().getId();
        }
        this.recurrenceDetails = goal.getRecurringGoal() != null
                ? new RecurringGoalResponse(goal.getRecurringGoal())
                : null;
        this.deadlineDetails = goal.getDeadlineGoal() != null
                ? new DeadlineGoalResponse(goal.getDeadlineGoal())
                : null;
        this.repetitionDetails = goal.getRepeatingGoal() != null
                ? new RepeatingGoalResponse(goal.getRepeatingGoal())
                : null;
        this.scheduleDetails = goal.getScheduledGoal() != null
                ? new ScheduledGoalResponse(goal.getScheduledGoal())
                : null;
        this.stackDetails = goal.getStackableGoal() != null
                ? new StackableGoalResponse(goal.getStackableGoal())
                : null;
    }
}
