package com.bbjM.task.goal.domain.requests.patch_goal_request;

import com.bbjM.task.goal.domain.GoalType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ScheduledPatchGoalRequest implements PatchGoalBehaviorRequest {
    private Instant momentToExecute;

    @Override
    @Schema(example = "SCHEDULED")
    public GoalType getGoalType() {
        return GoalType.SCHEDULED;
    }
}
