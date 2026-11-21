package com.bbjM.task.goal.domain.decorators;

import com.bbjM.task.goal.domain.BaseGoal;
import com.bbjM.task.goal.domain.DeadlineGoal;
import com.bbjM.task.goal.domain.Goal;

public class DeadlineGoalDecorator extends GoalDecorator {
    private final BaseGoal baseGoal;

    public DeadlineGoalDecorator(Goal baseGoal) {
        super(baseGoal);
        this.baseGoal = (BaseGoal) baseGoal;
    }

    @Override
    public void accomplish() {
        DeadlineGoal deadlineGoal = baseGoal.getDeadlineGoal();
        if (deadlineGoal == null)
            throw new IllegalStateException("Esse objetivo não tem data limite!");

        super.accomplish();
    }
}
