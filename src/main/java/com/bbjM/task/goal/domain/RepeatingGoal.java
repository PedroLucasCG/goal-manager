package com.bbjM.task.goal.domain;

import com.bbjM.task.goal.application.api.requests.create_goal_request.CreateRepeatingGoalRequest;
import jakarta.persistence.*;

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

    public RepeatingGoal(CreateRepeatingGoalRequest createRepeatingGoalRequest, BaseGoal baseGoal) {
        this.goal = baseGoal;
        this.repeationCounter = createRepeatingGoalRequest.getRepeationCounter();
        this.repeationLimit = createRepeatingGoalRequest.getRepeationLimit();
    }
}
