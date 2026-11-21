package com.bbjM.task.goal.domain;

import com.bbjM.task.goal.domain.decorators.*;

public interface Goal {
    void accomplish();

    static Goal decorateGoalByType(BaseGoal goal) {
        Goal goalWrapped = goal;
        for (GoalType goalType : goal.getGoalType()) {
            goalWrapped = getGoalWrapped(goalType, goalWrapped);
        }
        return goalWrapped;
    }

    private static Goal getGoalWrapped(GoalType goalType, Goal goal) {
        return switch (goalType) {
            case RECURRING -> new RecurringGoalDecorator(goal);
            case DEADLINE -> new DeadlineGoalDecorator(goal);
            case REPEATING -> new RepeatingGoalDecorator(goal);
            case SCHEDULED -> new ScheduleGoalDecorator(goal);
            case STACKABLE -> new StackableGoalDecorator(goal);
            case SIMPLE -> goal;
            default -> throw new IllegalStateException("Unexpected value: " + goalType);
        };
    }
}
