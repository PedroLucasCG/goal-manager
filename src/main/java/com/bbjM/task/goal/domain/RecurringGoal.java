package com.bbjM.task.goal.domain;

import com.bbjM.task.goal.application.api.requests.create_goal_request.CreateRecurringGoalRequest;
import com.bbjM.task.goal.application.api.requests.patch_goal_request.PatchRecurringGoalRequest;
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
    private LocalDateTime recurrenceMoment;

    public RecurringGoal(CreateRecurringGoalRequest createGoalRequest, BaseGoal baseGoal) {
        goal = baseGoal;
        interval = createGoalRequest.getDuration();
        recurrenceMoment = LocalDateTime.now().plus(interval);
    }

    public void applyPatch(PatchRecurringGoalRequest patchRecurringGoalRequest) {
        if (patchRecurringGoalRequest.getInterval() != null) interval = patchRecurringGoalRequest.getInterval();
    }

    public void nextCycle() {
        recurrenceMoment = LocalDateTime.now().plus(interval);
        goal.schedule(recurrenceMoment);
    }
}
