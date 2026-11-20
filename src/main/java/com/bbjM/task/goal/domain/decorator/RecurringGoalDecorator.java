package com.bbjM.task.goal.domain.decorator;

import com.bbjM.task.goal.domain.BaseGoal;
import com.bbjM.task.goal.domain.Goal;

public class RecurringGoalDecorator extends GoalDecorator{
    private final BaseGoal baseGoal;

    public RecurringGoalDecorator(Goal baseGoal) {
        super(baseGoal);
        this.baseGoal = (BaseGoal) baseGoal;
    }

    @Override
    public void accomplish() {
        super.accomplish();
        baseGoal.reschedule();
    }
}
