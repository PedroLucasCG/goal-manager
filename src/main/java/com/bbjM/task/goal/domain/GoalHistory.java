package com.bbjM.task.goal.domain;

import com.bbjM.task.shared.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@Table(name = "goal_history")
public class GoalHistory extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false)
    private UUID goalId;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private String color;
    @Column(nullable = false)
    private Status status;
    @Column(nullable = false)
    private Long level;

    @OneToMany(mappedBy = "goalHistory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GoalBehaviorSnapshot> goalBehaviorSnapshots;

}
