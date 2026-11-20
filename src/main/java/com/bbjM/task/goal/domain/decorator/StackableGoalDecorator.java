package com.bbjM.task.goal.domain.decorator;

import com.bbjM.task.goal.domain.BaseGoal;
import com.bbjM.task.goal.domain.Goal;
import com.bbjM.task.goal.domain.StackableGoal;

public class StackableGoalDecorator extends GoalDecorator {
    private final BaseGoal baseGoal;

    public StackableGoalDecorator(Goal baseGoal) {
        super(baseGoal);
        this.baseGoal = (BaseGoal) baseGoal;
    }

    @Override
    public void accomplish() {
        StackableGoal stackableGoal = baseGoal.getStackableGoal();
        if (stackableGoal == null)
            throw new IllegalStateException("Esse objetivo não possui cota!");

        super.accomplish();
    }
}
