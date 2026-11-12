package com.bbjM.task.goal.infra;

import com.bbjM.task.goal.application.api.requests.GoalFilterRequest;
import com.bbjM.task.goal.domain.BaseGoal;
import com.bbjM.task.user.domain.Credential;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Log4j2
@RequiredArgsConstructor
public class GoalInfraRepository implements GoalRepository {
    private final GoalPostgresRepository goalPostgresRepository;

    @Override
    public BaseGoal saveGoal(BaseGoal goal) {
        log.info("[start] GoalInfraRepository - saveGoal");
        BaseGoal savedGoal = goalPostgresRepository.save(goal);
        log.debug("[finish] GoalInfraRepository - saveGoal");
        return savedGoal;
    }

    @Override
    public Optional<BaseGoal> findGoalById(UUID id, Credential owner) {
        log.info("[start] GoalInfraRepository - findGoalById");
        Optional<BaseGoal> goal = goalPostgresRepository.findGoalByIdAndOwner(id, owner);
        log.debug("[finish] GoalInfraRepository - findGoalById");
        return goal;
    }

    @Override
    public List<BaseGoal> findRootGoalsByUserEmail(String email, GoalFilterRequest goalFilterRequest) {
        log.info("[start] GoalInfraRepository - findRootGoalsByUserEmail");
        List<BaseGoal> goals = goalPostgresRepository.findGoalHierarchyByEmail(email, goalFilterRequest);
        log.debug("[finish] GoalInfraRepository - findRootGoalsByUserEmail");
        return goals;
    }

    @Override
    public void deleteGoal(BaseGoal goalToDelete) {
        log.info("[start] GoalInfraRepository - deleteGoal");
        goalPostgresRepository.delete(goalToDelete);
        log.debug("[finish] GoalInfraRepository - deleteGoal");
    }
}
