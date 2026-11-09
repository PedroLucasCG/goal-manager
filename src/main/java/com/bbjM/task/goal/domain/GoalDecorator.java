package com.bbjM.task.goal.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public abstract class GoalDecorator implements Goal{
    private final Goal baseGoal;

    protected GoalDecorator(Goal baseGoal) {
        this.baseGoal = baseGoal;
    }

    @Override
    public void accomplish() {
        baseGoal.accomplish();
    }

}
