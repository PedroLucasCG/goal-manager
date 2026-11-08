package com.bbjM.task.goal.domain;

import com.bbjM.task.goal.application.api.requests.CreateGoalRequest;
import com.bbjM.task.shared.Auditable;
import com.bbjM.task.user.domain.Credential;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
public class Goal extends Auditable {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String description;
    private String color;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private Credential owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_goal")
    private Goal parentGoal;

    @OneToMany(mappedBy = "parentGoal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Goal> children;

    public Goal(CreateGoalRequest createGoalRequest, Credential owner, Goal parentGoal) {
        this.title = createGoalRequest.getTitle();
        this.description = createGoalRequest.getDescription();
        this.color = createGoalRequest.getColor();
        this.parentGoal = parentGoal;
        this.owner = owner;
        this.status = Status.IN_PROGRESS;
    }
}
