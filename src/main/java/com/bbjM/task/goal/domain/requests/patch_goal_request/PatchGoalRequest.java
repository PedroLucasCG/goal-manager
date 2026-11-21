package com.bbjM.task.goal.domain.requests.patch_goal_request;

import com.bbjM.task.goal.domain.GoalType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "goalType",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = PatchSimpleGoalRequest.class, name = "SIMPLE"),
        @JsonSubTypes.Type(value = PatchRecurringGoalRequest.class, name = "RECURRING")
})
public class PatchGoalRequest {
    private String title;
    private String description;
    private String color;
    @NotNull(message = "O tipo de objetivo é obrigatório!")
    private List<GoalType> goalType;
}
