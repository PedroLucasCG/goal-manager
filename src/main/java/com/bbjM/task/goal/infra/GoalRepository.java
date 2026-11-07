package com.bbjM.task.goal.infra;

import com.bbjM.task.goal.domain.Goal;
import com.bbjM.task.user.domain.Credential;

import java.util.Optional;
import java.util.UUID;

public interface GoalRepository {
    Goal saveGoal(Goal goal);

    Optional<Goal> findGoalById(UUID id, Credential owner);
}
