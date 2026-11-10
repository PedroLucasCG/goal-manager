package com.bbjM.task.goal.domain;

import com.bbjM.task.goal.application.api.requests.CreateRecurringGoalRequest;
import com.bbjM.task.goal.application.api.requests.PatchRecurringGoalRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "goal_recurrence")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class RecurringGoal {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @OneToOne(fetch = FetchType.LAZY)
    private BaseGoal goal;
    @Column(nullable = false)
    private Duration interval;
    @Column(nullable = false)
    private LocalDateTime rescheduleDateTime;

    public RecurringGoal(CreateRecurringGoalRequest createGoalRequest, BaseGoal baseGoal) {
        this.goal = baseGoal;
        this.interval = createGoalRequest.getDuration();
        this.rescheduleDateTime = LocalDateTime.now();
    }

    public void nextCycle() {
        rescheduleDateTime.plus(interval);
    }

    public void applyPatch(PatchRecurringGoalRequest patchRecurringGoalRequest) {
        if (patchRecurringGoalRequest.getInterval() != null) this.interval = patchRecurringGoalRequest.getInterval();
        if (patchRecurringGoalRequest.getRescheduleTime() != null)
            this.rescheduleDateTime = patchRecurringGoalRequest.getRescheduleTime();
    }
}
