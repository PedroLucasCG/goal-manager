package com.bbjM.task.goal.domain;

import com.bbjM.task.goal.application.api.requests.create_goal_request.CreateScheduledGoal;
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

    public ScheduledGoal(CreateScheduledGoal createScheduledGoal, BaseGoal baseGoal) {
        this.goal = baseGoal;
        this.momentToExecute = createScheduledGoal.getMomentToExecute();
    }
}
