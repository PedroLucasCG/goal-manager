package com.bbjM.task.goal.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class Goal {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    private String color;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_goal")
    private Goal parentGoal;

    @OneToMany(mappedBy = "parentGoal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Goal> children;
}
