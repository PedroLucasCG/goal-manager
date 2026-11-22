package com.bbjM.task.goal.domain;

import com.bbjM.task.goal.domain.requests.create_goal_request.CreateGoalBehaviorRequest;
import com.bbjM.task.goal.domain.requests.create_goal_request.DeadlineCreateGoalRequest;
import com.bbjM.task.goal.domain.requests.create_goal_request.ScheduledCreateGoalRequest;
import com.bbjM.task.goal.domain.requests.patch_goal_request.DeadlinePatchGoalRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "goal_deadline")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class DeadlineGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne(fetch = FetchType.LAZY)
    private BaseGoal goal;
    @Column(nullable = false)
    private Instant deadline;

    public DeadlineGoal(DeadlineCreateGoalRequest createDeadlineGoalRequest, BaseGoal baseGoal) {
        this.goal = baseGoal;
        this.deadline = createDeadlineGoalRequest.getDeadline();
    }

    public DeadlineGoal(DeadlinePatchGoalRequest deadlinePatchGoalRequest) {
        this.deadline = deadlinePatchGoalRequest.getDeadline();
    }

    public void applyPatch(DeadlinePatchGoalRequest patchGoalBehaviorRequest) {
        this.deadline = patchGoalBehaviorRequest.getDeadline();
    }

    public static CreateGoalBehaviorRequest fromPatch(DeadlinePatchGoalRequest deadlinePatchGoalRequest) {
        return new DeadlineCreateGoalRequest(new DeadlineGoal(deadlinePatchGoalRequest));
    }

    public record DeadlineState(Instant momentToExecute) {}

    public DeadlineState dumpState() {
        return new DeadlineState(this.deadline);
    }
}
