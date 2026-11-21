package com.bbjM.task.goal.domain.requests.create_goal_request;

import com.bbjM.task.goal.domain.GoalType;
import jakarta.validation.constraints.NotBlank;
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
    private List<GoalType> goalType;
    private List<CreateGoalBehaviorRequest> behaviors;
}
