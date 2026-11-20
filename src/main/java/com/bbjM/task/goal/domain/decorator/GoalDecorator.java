package com.bbjM.task.goal.domain.decorator;

import com.bbjM.task.goal.domain.Goal;
import lombok.Getter;

@Getter
public abstract class GoalDecorator implements Goal {
    private final Goal baseGoal;

    protected GoalDecorator(Goal baseGoal) {
        this.baseGoal = baseGoal;
    }

    @Override
    public void accomplish() {
        baseGoal.accomplish();
    }

}
