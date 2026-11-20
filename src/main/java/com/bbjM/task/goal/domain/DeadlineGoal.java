package com.bbjM.task.goal.domain;

import com.bbjM.task.goal.application.api.requests.create_goal_request.CreateDeadlineGoalRequest;
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

    public DeadlineGoal(CreateDeadlineGoalRequest createDeadlineGoalRequest, BaseGoal baseGoal) {
        this.goal = baseGoal;
        this.deadline = createDeadlineGoalRequest.getDeadline();
    }
}
