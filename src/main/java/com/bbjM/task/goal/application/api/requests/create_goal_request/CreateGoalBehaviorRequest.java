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
        @JsonSubTypes.Type(value = CreateRecurringGoalRequest.class, name = "RECURRING"),
        @JsonSubTypes.Type(value = CreateScheduledGoalRequest.class, name = "SCHEDULED"),
        @JsonSubTypes.Type(value = CreateStackableGoalRequest.class, name = "STACKABLE"),
        @JsonSubTypes.Type(value = CreateRepeatingGoalRequest.class, name = "REPEATING"),
        @JsonSubTypes.Type(value = CreateDeadlineGoalRequest.class, name = "DEADLINE")
})
public interface CreateGoalBehaviorRequest {
    GoalType getGoalType();
}
