package com.bbjM.task.goal.infra;

import com.bbjM.task.goal.domain.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface GoalPostgresRepository extends JpaRepository<Goal, UUID> {
    Optional<Goal> findGoalById(UUID id);
}
