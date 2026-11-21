package com.bbjM.task.goal.domain;

import com.bbjM.task.goal.domain.requests.create_goal_request.StackableGoalRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "goal_stack")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class StackableGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne(fetch = FetchType.LAZY)
    private BaseGoal goal;
    @Column(nullable = false)
    private Long quantity;
    @Column(nullable = false)
    private Long stackLimit;

    public StackableGoal(StackableGoalRequest createStackableGoalRequest, BaseGoal baseGoal) {
        this.goal = baseGoal;
        this.quantity = createStackableGoalRequest.getQuantity();
        this.stackLimit = createStackableGoalRequest.getStackLimit();
    }
}
