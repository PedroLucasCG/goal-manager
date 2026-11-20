package com.bbjM.task.goal.domain;

import com.bbjM.task.goal.application.api.requests.CreateScheduledGoal;
import com.bbjM.task.goal.application.api.requests.RepeatingGoalRequest;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

public class RepeatingGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne(fetch = FetchType.LAZY)
    private BaseGoal goal;
    @Column(nullable = false)
    private Long repeationCounter;
    @Column(nullable = false)
    private Long repeationLimit;

    public RepeatingGoal(RepeatingGoalRequest repeatingGoalRequest, BaseGoal baseGoal) {
        this.goal = baseGoal;
        this.repeationCounter = repeatingGoalRequest.getRepeationCounter();
        this.repeationLimit = repeatingGoalRequest.getRepeationLimit();
    }
}
