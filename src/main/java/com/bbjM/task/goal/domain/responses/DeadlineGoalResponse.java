package com.bbjM.task.goal.domain.responses;

import com.bbjM.task.goal.domain.DeadlineGoal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DeadlineGoalResponse {
    private Instant deadline;

    public DeadlineGoalResponse(DeadlineGoal deadlineGoal) {
        this.deadline = deadlineGoal.getDeadline();
    }
}
