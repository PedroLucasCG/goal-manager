package com.bbjM.task.goal.domain.responses;

import com.bbjM.task.goal.domain.StackableGoal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StackableGoalResponse {
    private Long quantity;
    private Long stackLimit;

    public StackableGoalResponse(StackableGoal stackableGoal) {
        this.quantity = stackableGoal.getQuantity();
        this.stackLimit = stackableGoal.getStackLimit();
    }
}
