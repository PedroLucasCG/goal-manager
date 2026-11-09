package com.bbjM.task.goal.domain;

public class RecurringGoal extends GoalDecorator{
    private final BaseGoal baseGoal;

    public RecurringGoal(Goal baseGoal) {
        super(baseGoal);
        this.baseGoal = (BaseGoal) baseGoal;
    }

    @Override
    public void accomplish() {
        super.accomplish();
        baseGoal.reschedule();
    }
}
