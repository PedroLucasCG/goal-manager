package com.bbjM.task.goal.domain.requests.create_goal_request;

import com.bbjM.task.goal.domain.GoalType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
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
    @NotEmpty(message = "Tem que ter pelo menos um comportamento!")
    private List<CreateGoalBehaviorRequest> behaviors;

    @Schema(hidden = true)
    public List<GoalType> getGoalTypes() {
        return behaviors.stream()
                .map(CreateGoalBehaviorRequest::getGoalType)
                .toList();
    }
}
