package com.bbjM.task.goal.domain.requests.patch_goal_request;

import com.bbjM.task.goal.domain.BaseGoal;
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
        @JsonSubTypes.Type(value = SimplePatchGoalRequest.class, name = "SIMPLE"),
        @JsonSubTypes.Type(value = RecurringPatchGoalRequest.class, name = "RECURRING"),
        @JsonSubTypes.Type(value = ScheduledPatchGoalRequest.class, name = "SCHEDULED"),
        @JsonSubTypes.Type(value = StackablePatchGoalRequest.class, name = "STACKABLE"),
        @JsonSubTypes.Type(value = RepeatingPatchGoalRequest.class, name = "REPEATING"),
        @JsonSubTypes.Type(value = DeadlinePatchGoalRequest.class, name = "DEADLINE")
})
public interface PatchGoalBehaviorRequest {
    GoalType getGoalType();
}
