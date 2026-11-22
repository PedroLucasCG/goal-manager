package com.bbjM.task.goal.domain;

import com.bbjM.task.goal.domain.decorators.*;

public interface Goal {
    void accomplish();

    static Goal decorateGoalByType(BaseGoal goal) {
        Goal goalWrapped = goal;
        for (GoalType goalType : goal.getGoalTypes()) {
            goalWrapped = getGoalWrapped(goalType, goalWrapped, goal);
        }
        return goalWrapped;
    }

    private static Goal getGoalWrapped(GoalType goalType, Goal goal, BaseGoal baseGoal) {
        return switch (goalType) {
            case RECURRING -> new RecurringGoalDecorator(goal, baseGoal);
            case DEADLINE -> new DeadlineGoalDecorator(goal, baseGoal);
            case REPEATING -> new RepeatingGoalDecorator(goal, baseGoal);
            case SCHEDULED -> new ScheduledGoalDecorator(goal, baseGoal);
            case STACKABLE -> new StackableGoalDecorator(goal, baseGoal);
            case SIMPLE -> goal;
            default -> throw new IllegalStateException("Unexpected value: " + goalType);
        };
    }
}
