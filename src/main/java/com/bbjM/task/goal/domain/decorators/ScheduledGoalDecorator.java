package com.bbjM.task.goal.domain.decorators;

import com.bbjM.task.goal.domain.BaseGoal;
import com.bbjM.task.goal.domain.Goal;
import com.bbjM.task.goal.domain.ScheduledGoal;

public class ScheduledGoalDecorator extends GoalDecorator{
    private final BaseGoal baseGoal;

    public ScheduledGoalDecorator(Goal baseGoal) {
        super(baseGoal);
        this.baseGoal = (BaseGoal) baseGoal;
    }

    @Override
    public void accomplish() {
        ScheduledGoal scheduledGoal = baseGoal.getScheduledGoal();
        if (scheduledGoal == null)
            throw new IllegalStateException("Esse objetivo não pode ser programado!");

        super.accomplish();
    }
}
