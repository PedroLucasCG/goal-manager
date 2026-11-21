package com.bbjM.task.goal.domain;

import com.bbjM.task.goal.domain.requests.create_goal_request.RepeatingGoalRequest;
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

    public RepeatingGoal(RepeatingGoalRequest createRepeatingGoalRequest, BaseGoal baseGoal) {
        this.goal = baseGoal;
        this.repetitionCounter = createRepeatingGoalRequest.getRepetitionCounter();
        this.repetitionLimit = createRepeatingGoalRequest.getRepetitionLimit();
    }
}
