package com.bbjM.task.goal.domain;

import com.bbjM.task.goal.domain.requests.create_goal_request.ScheduledGoalRequest;
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

    public ScheduledGoal(ScheduledGoalRequest createScheduledGoalRequest, BaseGoal baseGoal) {
        this.goal = baseGoal;
        this.momentToExecute = createScheduledGoalRequest.getMomentToExecute();
    }
}
