package com.bbjM.task.goal.domain.requests.patch_goal_request;

import com.bbjM.task.goal.domain.GoalType;
import com.bbjM.task.goal.domain.requests.create_goal_request.*;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "goalType",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = SimpleCreateGoalRequest.class, name = "SIMPLE"),
        @JsonSubTypes.Type(value = RecurringCreateGoalRequest.class, name = "RECURRING"),
        @JsonSubTypes.Type(value = ScheduledCreateGoalRequest.class, name = "SCHEDULED"),
        @JsonSubTypes.Type(value = StackableCreateGoalRequest.class, name = "STACKABLE"),
        @JsonSubTypes.Type(value = RepeatingCreateGoalRequest.class, name = "REPEATING"),
        @JsonSubTypes.Type(value = DeadlineCreateGoalRequest.class, name = "DEADLINE")
})
public interface PatchGoalBehaviorRequest {
    GoalType getGoalType();
}
