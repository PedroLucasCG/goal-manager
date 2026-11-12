package com.bbjM.task.goal.infra;

import com.bbjM.task.goal.application.api.requests.GoalFilterRequest;
import com.bbjM.task.goal.domain.BaseGoal;
import com.bbjM.task.user.domain.Credential;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GoalRepository {
    BaseGoal saveGoal(BaseGoal goal);

    Optional<BaseGoal> findGoalById(UUID id, Credential owner);

    List<BaseGoal> findRootGoalsByUserEmail(String email, GoalFilterRequest goalFilterRequest);

    void deleteGoal(BaseGoal goalToDelete);
}
