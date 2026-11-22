package com.bbjM.task.goal.domain;

import com.bbjM.task.goal.domain.requests.create_goal_request.CreateGoalBehaviorRequest;
import com.bbjM.task.goal.domain.requests.create_goal_request.DeadlineCreateGoalRequest;
import com.bbjM.task.goal.domain.requests.create_goal_request.RepeatingCreateGoalRequest;
import com.bbjM.task.goal.domain.requests.patch_goal_request.RepeatingPatchGoalRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "goal_repeat")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class RepeatingGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne(fetch = FetchType.LAZY)
    private BaseGoal goal;
    @Column(nullable = false)
    private Long repetitionCounter;
    @Column(nullable = false)
    private Long repetitionLimit;

    public RepeatingGoal(RepeatingCreateGoalRequest createRepeatingGoalRequest, BaseGoal baseGoal) {
        this.goal = baseGoal;
        this.repetitionCounter = createRepeatingGoalRequest.getRepetitionCounter();
        this.repetitionLimit = createRepeatingGoalRequest.getRepetitionLimit();
    }

    public RepeatingGoal(RepeatingPatchGoalRequest repeatingPatchGoalRequest) {
        this.repetitionCounter = repeatingPatchGoalRequest.getRepetitionCounter();
        this.repetitionLimit = repeatingPatchGoalRequest.getRepetitionLimit();
    }

    public void applyPatch(RepeatingPatchGoalRequest patchGoalBehaviorRequest) {
        this.repetitionCounter = patchGoalBehaviorRequest.getRepetitionCounter();
        this.repetitionLimit = patchGoalBehaviorRequest.getRepetitionLimit();
    }

    public static CreateGoalBehaviorRequest fromPatch(RepeatingPatchGoalRequest repeatingPatchGoalRequest) {
        return new RepeatingCreateGoalRequest(new RepeatingGoal(repeatingPatchGoalRequest));
    }

    public record RepeatingState(Long repetitionCounter, Long repetitionLimit) {}

    public Object dumpState() {
        return new RepeatingState(repetitionCounter, repetitionLimit);
    }
}
