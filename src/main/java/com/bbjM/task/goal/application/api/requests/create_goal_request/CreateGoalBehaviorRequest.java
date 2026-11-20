package com.bbjM.task.goal.application.api.requests.create_goal_request;

import com.bbjM.task.goal.domain.GoalType;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "goalType",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = CreateSimpleGoalRequest.class, name = "SIMPLE"),
        @JsonSubTypes.Type(value = CreateRecurringGoalRequest.class, name = "RECURRING")
})
public interface CreateGoalBehaviorRequest {
    GoalType getGoalType();
}
