package com.bbjM.task.goal.infra;

import com.bbjM.task.goal.domain.Goal;

public interface GoalRepository {
    Goal saveGoal(Goal goal);
}
