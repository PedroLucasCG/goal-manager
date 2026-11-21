package com.bbjM.task.goal.domain;

import com.bbjM.task.goal.domain.requests.create_goal_request.CreateGoalBehaviorRequest;
import com.bbjM.task.goal.domain.requests.create_goal_request.RepeatingCreateGoalRequest;
import com.bbjM.task.goal.domain.requests.create_goal_request.StackableCreateGoalRequest;
import com.bbjM.task.goal.domain.requests.patch_goal_request.StackablePatchGoalRequest;
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

    public StackableGoal(StackableCreateGoalRequest createStackableGoalRequest, BaseGoal baseGoal) {
        this.goal = baseGoal;
        this.quantity = createStackableGoalRequest.getQuantity();
        this.stackLimit = createStackableGoalRequest.getStackLimit();
    }

    public StackableGoal(StackablePatchGoalRequest stackablePatchGoalRequest) {
        this.quantity = stackablePatchGoalRequest.getQuantity();
        this.stackLimit = stackablePatchGoalRequest.getStackLimit();
    }

    public void applyPatch(StackablePatchGoalRequest patchGoalBehaviorRequest) {
        this.quantity = patchGoalBehaviorRequest.getQuantity();
        this.stackLimit = patchGoalBehaviorRequest.getStackLimit();
    }

    public static CreateGoalBehaviorRequest fromPatch(StackablePatchGoalRequest stackablePatchGoalRequest) {
        return new StackableCreateGoalRequest(new StackableGoal(stackablePatchGoalRequest));
    }
}
