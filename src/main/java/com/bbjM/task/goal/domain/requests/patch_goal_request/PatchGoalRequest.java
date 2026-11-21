package com.bbjM.task.goal.domain.requests.patch_goal_request;

import com.bbjM.task.goal.domain.GoalType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PatchGoalRequest {
    private String title;
    private String description;
    private String color;
    @NotEmpty(message = "Tem que ter pelo menos um comportamento!")
    private List<PatchGoalBehaviorRequest> behaviors;

    @Schema(hidden = true)
    public List<GoalType> getGoalTypes() {
        return behaviors.stream()
                .map(PatchGoalBehaviorRequest::getGoalType)
                .toList();
    }
}
