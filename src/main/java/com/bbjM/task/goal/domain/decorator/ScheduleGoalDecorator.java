package com.bbjM.task.goal.domain.decorator;

import com.bbjM.task.goal.domain.BaseGoal;
import com.bbjM.task.goal.domain.Goal;
import com.bbjM.task.goal.domain.RecurringGoal;
import com.bbjM.task.goal.domain.ScheduledGoal;

public class ScheduleGoalDecorator extends GoalDecorator{
    private final BaseGoal baseGoal;

    public ScheduleGoalDecorator(Goal baseGoal) {
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
