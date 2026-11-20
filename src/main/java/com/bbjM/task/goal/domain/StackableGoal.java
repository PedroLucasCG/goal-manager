package com.bbjM.task.goal.domain;

import com.bbjM.task.goal.application.api.requests.create_goal_request.CreateStackableGoalRequest;
import jakarta.persistence.*;

import java.util.UUID;

public class StackableGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne(fetch = FetchType.LAZY)
    private BaseGoal goal;
    @Column(nullable = false)
    private Long quantity;

    public StackableGoal(CreateStackableGoalRequest createStackableGoalRequest, BaseGoal baseGoal) {
        this.goal = baseGoal;
        this.quantity = createStackableGoalRequest.getQuantity();
    }
}
