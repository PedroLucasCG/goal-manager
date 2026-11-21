package com.bbjM.task.goal.domain.decorators;

import com.bbjM.task.goal.domain.BaseGoal;
import com.bbjM.task.goal.domain.Goal;
import com.bbjM.task.goal.domain.RepeatingGoal;

public class RepeatingGoalDecorator extends GoalDecorator {
    private final BaseGoal baseGoal;

    public RepeatingGoalDecorator(Goal baseGoal) {
        super(baseGoal);
        this.baseGoal = (BaseGoal) baseGoal;
    }

    @Override
    public void accomplish() {
        RepeatingGoal repeatingGoal = baseGoal.getRepeatingGoal();
        if (repeatingGoal == null)
            throw new IllegalStateException("Esse objetivo não é de repetição!");

        super.accomplish();
    }
}
