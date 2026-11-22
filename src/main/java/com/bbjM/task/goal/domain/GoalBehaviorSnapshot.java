package com.bbjM.task.goal.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@Table(name = "goal_behavior_snapshot")
public class GoalBehaviorSnapshot {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "goal_history")
    private GoalHistory goalHistory;

    @Column(nullable = false)
    private GoalType goalType;

    @Column(nullable = false)
    private String state;
}
