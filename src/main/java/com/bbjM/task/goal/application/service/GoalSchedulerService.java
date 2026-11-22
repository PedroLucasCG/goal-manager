package com.bbjM.task.goal.application.service;

import com.bbjM.task.goal.domain.GoalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Log4j2
public class GoalSchedulerService {
    private final GoalRepository goalRepository;

    @Scheduled(cron = "0 0 0 * * *", zone = "America/Bahia")
    public int startCycleForRecurringGoals() {
        log.info("[start] GoalSchedulerService - startCycleForRecurringGoals");
        int goalsActivated = goalRepository.scanAndActivateDueRecurringGoals();
        log.debug("[finish] GoalSchedulerService - startCycleForRecurringGoals");
        return goalsActivated;
    }

    @Scheduled(cron = "0 0 0 * * *", zone = "America/Bahia")
    public int activateScheduledGoals() {
        log.info("[start] GoalSchedulerService - activateScheduledGoals");
        int goalsActivated = goalRepository.scanAndActivateDueScheduledGoals();
        log.debug("[finish] GoalSchedulerService - activateScheduledGoals");
        return goalsActivated;
    }
}
