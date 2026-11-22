package com.bbjM.task.goal.infra;

import com.bbjM.task.goal.domain.BaseGoal;
import com.bbjM.task.goal.domain.GoalHistory;
import com.bbjM.task.goal.domain.GoalRepository;
import com.bbjM.task.goal.domain.requests.GoalFilterRequest;
import com.bbjM.task.user.domain.Credential;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
@Log4j2
@RequiredArgsConstructor
public class GoalInfraRepository implements GoalRepository {
    private final GoalPostgresRepository goalPostgresRepository;
    private final GoalHistoryPostgresRepository goalHistoryPostgresRepository;

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
    @Transactional
    public void deleteGoal(BaseGoal goalToDelete) {
        log.info("[start] GoalInfraRepository - deleteGoal");
        int deletedLines = goalPostgresRepository.deleteGoalById(goalToDelete.getId());
        log.info("[deleteGoal] deleted lines - {}", deletedLines);
        log.debug("[finish] GoalInfraRepository - deleteGoal");
    }

    @Override
    public GoalHistory updateGoalHistory(BaseGoal goal) {
        log.info("[start] GoalInfraRepository - makeGoalHistory");
        GoalHistory goalHistory = new GoalHistory(goal);
        goalHistoryPostgresRepository.save(goalHistory);
        log.debug("[finish] GoalInfraRepository - makeGoalHistory");
        return goalHistory;
    }

    @Override
    @Transactional
    public int scanAndActivateDueRecurringGoals() {
        log.info("[start] GoalInfraRepository - scanAndActivateDueGoals");
        int goalsUpdated = goalPostgresRepository.updateRecurringGoals(LocalDateTime.now());
        log.debug("[finish] GoalInfraRepository - scanAndActivateDueGoals");
        return goalsUpdated;
    }

    @Override
    public int scanAndActivateDueScheduledGoals() {
        log.info("[start] GoalInfraRepository - scanAndActivateDueScheduledGoals");
        int goalsUpdated = goalPostgresRepository.updateScheduledGoals(LocalDateTime.now());
        log.debug("[finish] GoalInfraRepository - scanAndActivateDueScheduledGoals");
        return goalsUpdated;
    }
}
