package com.bbjM.task.goal.infra;

import com.bbjM.task.goal.application.api.requests.GoalFilterRequest;
import com.bbjM.task.goal.domain.Goal;
import com.bbjM.task.user.domain.Credential;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GoalRepository {
    Goal saveGoal(Goal goal);

    Optional<Goal> findGoalById(UUID id, Credential owner);

    List<Goal> findRootGoalsByUserEmail(String email, GoalFilterRequest goalFilterRequest);
}
