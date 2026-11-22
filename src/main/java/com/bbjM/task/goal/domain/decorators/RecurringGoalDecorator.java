package com.bbjM.task.goal.domain.decorators;

import com.bbjM.task.goal.domain.BaseGoal;
import com.bbjM.task.goal.domain.Goal;
import com.bbjM.task.goal.domain.RecurringGoal;

public class RecurringGoalDecorator extends GoalDecorator{
    private final BaseGoal baseGoal;

    public RecurringGoalDecorator(Goal goal, BaseGoal baseGoal) {
        super(goal);
        this.baseGoal = baseGoal;
    }

    @Override
    public void accomplish() {
        RecurringGoal recurringGoal = baseGoal.getRecurringGoal();
        if (recurringGoal == null)
            throw new IllegalStateException("Esse objetivo não pode ser reprogramado!");

        recurringGoal.nextCycle();
        super.accomplish();
    }
}
