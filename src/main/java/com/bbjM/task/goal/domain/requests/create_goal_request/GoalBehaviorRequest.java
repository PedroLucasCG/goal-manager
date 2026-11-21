package com.bbjM.task.goal.domain.requests.create_goal_request;

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
        @JsonSubTypes.Type(value = SimpleGoalRequest.class, name = "SIMPLE"),
        @JsonSubTypes.Type(value = RecurringGoalRequest.class, name = "RECURRING"),
        @JsonSubTypes.Type(value = ScheduledGoalRequest.class, name = "SCHEDULED"),
        @JsonSubTypes.Type(value = StackableGoalRequest.class, name = "STACKABLE"),
        @JsonSubTypes.Type(value = RepeatingGoalRequest.class, name = "REPEATING"),
        @JsonSubTypes.Type(value = DeadlineGoalRequest.class, name = "DEADLINE")
})
public interface GoalBehaviorRequest {
    GoalType getGoalType();
}
