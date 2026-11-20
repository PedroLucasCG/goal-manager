package com.bbjM.task.goal.application.api.requests.create_goal_request;

import com.bbjM.task.goal.domain.GoalType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateGoalRequest {
    @NotBlank(message = "O objetivo precisa de um nome!")
    private String title;
    @NotBlank(message = "O objetivo precisa de uma descrição!")
    private String description;
    private String color;
    private UUID parentGoal;
    private GoalType goalType;
    private List<CreateGoalBehaviorRequest> behaviors;
}
