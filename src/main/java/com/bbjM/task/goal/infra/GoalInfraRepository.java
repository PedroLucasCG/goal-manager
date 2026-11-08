package com.bbjM.task.goal.infra;

import com.bbjM.task.goal.domain.Goal;
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
    public Goal saveGoal(Goal goal) {
        log.info("[start] GoalInfraRepository - saveGoal");
        Goal savedGoal = goalPostgresRepository.save(goal);
        log.debug("[finish] GoalInfraRepository - saveGoal");
        return savedGoal;
    }

    @Override
    public Optional<Goal> findGoalById(UUID id, Credential owner) {
        log.info("[start] GoalInfraRepository - findGoalById");
        Optional<Goal> goal = goalPostgresRepository.findGoalByIdAndOwner(id, owner);
        log.debug("[finish] GoalInfraRepository - findGoalById");
        return goal;
    }

    @Override
    public List<Goal> findRootGoalsByUserEmail(String email) {
        log.info("[start] GoalInfraRepository - findRootGoalsByUserEmail");
        List<Goal> goals = goalPostgresRepository.findGoalHierarchyByEmail(email);
        log.debug("[finish] GoalInfraRepository - findRootGoalsByUserEmail");
        return goals;
    }
}
