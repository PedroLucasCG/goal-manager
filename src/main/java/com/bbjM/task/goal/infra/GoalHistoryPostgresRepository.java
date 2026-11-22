package com.bbjM.task.goal.infra;

import com.bbjM.task.goal.domain.GoalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GoalHistoryPostgresRepository extends JpaRepository<GoalHistory, UUID> {
}
