package com.bbjM.task.goal.domain;

import com.bbjM.task.goal.application.api.requests.CreateScheduledGoal;
import com.bbjM.task.goal.application.api.requests.DeadlineGoalRequest;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

public class DeadlineGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne(fetch = FetchType.LAZY)
    private BaseGoal goal;
    @Column(nullable = false)
    private Instant deadline;

    public DeadlineGoal(DeadlineGoalRequest deadlineGoalRequest, BaseGoal baseGoal) {
        this.goal = baseGoal;
        this.deadline = deadlineGoalRequest.getDeadline();
    }
}
