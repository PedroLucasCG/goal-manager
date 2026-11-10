package com.bbjM.task.goal.domain;

import com.bbjM.task.goal.application.api.requests.CreateGoalRequest;
import com.bbjM.task.goal.application.api.requests.CreateRecurringGoalRequest;
import com.bbjM.task.shared.Auditable;
import com.bbjM.task.user.domain.Credential;
import jakarta.persistence.*;
import lombok.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@Table(name = "Goal")
public class BaseGoal extends Auditable implements Goal {
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
    @Column(nullable = false)
    private Long level;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private GoalType goalType;
    private Duration duration;
    private LocalDateTime dateTimeDelimiter;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private Credential owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_goal")
    private BaseGoal parentGoal;

    @OneToMany(mappedBy = "parentGoal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BaseGoal> children;

    public BaseGoal(CreateGoalRequest createGoalRequest, Credential owner, BaseGoal parentGoal) {
        this.title = createGoalRequest.getTitle();
        this.description = createGoalRequest.getDescription();
        this.color = createGoalRequest.getColor();
        this.parentGoal = parentGoal;
        this.owner = owner;
        this.status = Status.IN_PROGRESS;
        this.level = 0L;
        if(parentGoal != null) this.level = parentGoal.getLevel() + 1L;
        createOnGoalType(createGoalRequest);
    }

    private void createOnGoalType(CreateGoalRequest createGoalRequest) {
        switch (createGoalRequest.getGoalType()) {
            case RECURRING:
                this.goalType = GoalType.RECURRING;
                this.duration = ((CreateRecurringGoalRequest) createGoalRequest).getDuration();
                schedule(LocalDateTime.now());
                this.status = Status.IN_PROGRESS;
                break;
            default:
                this.goalType = GoalType.SIMPLE;
                break;
        }
    }

    public void reschedule() {
        if (duration == null)
            throw new IllegalStateException("Objetivos recorrentes precisam de duração para serem reprogramados");

        schedule(LocalDateTime.now());
        this.dateTimeDelimiter = this.dateTimeDelimiter.plus(duration);
    }

    public void schedule(LocalDateTime scheduledTime) {
        if (scheduledTime == null || scheduledTime.isBefore(LocalDateTime.now().minusMinutes(1)))
            throw new IllegalStateException("A data para programar objetivos deve ser válida!");
        this.dateTimeDelimiter = scheduledTime;
        this.status = Status.SCHEDULED;
    }

    @Override
    public void accomplish() {
        this.status = Status.COMPLETED;
    }
}
