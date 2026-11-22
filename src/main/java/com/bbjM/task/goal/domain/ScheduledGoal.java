package com.bbjM.task.goal.domain;

import com.bbjM.task.goal.domain.requests.create_goal_request.CreateGoalBehaviorRequest;
import com.bbjM.task.goal.domain.requests.create_goal_request.RecurringCreateGoalRequest;
import com.bbjM.task.goal.domain.requests.create_goal_request.ScheduledCreateGoalRequest;
import com.bbjM.task.goal.domain.requests.patch_goal_request.ScheduledPatchGoalRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "goal_schedule")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class ScheduledGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne(fetch = FetchType.LAZY)
    private BaseGoal goal;
    @Column(nullable = false)
    private Instant momentToExecute;

    public ScheduledGoal(ScheduledCreateGoalRequest createScheduledGoalRequest, BaseGoal baseGoal) {
        this.goal = baseGoal;
        this.momentToExecute = createScheduledGoalRequest.getMomentToExecute();
    }

    public ScheduledGoal(ScheduledPatchGoalRequest scheduledPatchGoalRequest) {
        this.momentToExecute = scheduledPatchGoalRequest.getMomentToExecute();
    }

    public void applyPatch(ScheduledPatchGoalRequest patchGoalBehaviorRequest) {
        this.momentToExecute = patchGoalBehaviorRequest.getMomentToExecute();
    }

    public static CreateGoalBehaviorRequest fromPatch(ScheduledPatchGoalRequest scheduledPatchGoalRequest) {
        return new ScheduledCreateGoalRequest(new ScheduledGoal(scheduledPatchGoalRequest));
    }

    public record ScheduleState(Instant momentToExecute) {}

    public ScheduleState dumpState() {
        return new ScheduleState(this.momentToExecute);
    }
}
