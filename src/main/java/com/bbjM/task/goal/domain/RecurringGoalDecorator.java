package com.bbjM.task.goal.domain;

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
