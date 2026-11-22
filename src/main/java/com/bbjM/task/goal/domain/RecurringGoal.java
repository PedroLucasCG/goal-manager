package com.bbjM.task.goal.domain;

import com.bbjM.task.goal.domain.requests.create_goal_request.CreateGoalBehaviorRequest;
import com.bbjM.task.goal.domain.requests.create_goal_request.RecurringCreateGoalRequest;
import com.bbjM.task.goal.domain.requests.patch_goal_request.RecurringPatchGoalRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;
import java.util.function.Supplier;

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

    public RecurringGoal(RecurringCreateGoalRequest createGoalRequest, BaseGoal baseGoal) {
        goal = baseGoal;
        interval = createGoalRequest.getDuration();
        recurrenceMoment = LocalDateTime.now().plus(interval);
    }

    public RecurringGoal(RecurringPatchGoalRequest recurringPatchGoalRequest) {
        this.interval = recurringPatchGoalRequest.getInterval();
    }

    public void applyPatch(RecurringPatchGoalRequest recurringPatchGoalRequest) {
        if (recurringPatchGoalRequest.getInterval() != null) interval = recurringPatchGoalRequest.getInterval();
    }

    public void nextCycle() {
        if(recurrenceMoment.isAfter(LocalDateTime.now()))
            throw new IllegalStateException("Não é possível começar o próximo ciclo!");
        recurrenceMoment = LocalDateTime.now().plus(interval);
        goal.schedule(recurrenceMoment);
    }

    public static CreateGoalBehaviorRequest fromPatch(RecurringPatchGoalRequest recurringPatchGoalRequest) {
        return new RecurringCreateGoalRequest(new RecurringGoal(recurringPatchGoalRequest));
    }

    public record DumpRecurring(Duration interval, LocalDateTime recurrenceMoment){}

    public DumpRecurring dumpState() {
        return new DumpRecurring(interval, recurrenceMoment);
    }
}
