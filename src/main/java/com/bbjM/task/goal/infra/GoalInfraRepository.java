package com.bbjM.task.goal.infra;

import com.bbjM.task.goal.domain.Goal;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Repository;

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
}
