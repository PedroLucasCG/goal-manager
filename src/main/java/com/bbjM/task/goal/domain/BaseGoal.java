package com.bbjM.task.goal.domain;

import com.bbjM.task.goal.application.api.requests.create_goal_request.CreateGoalRequest;
import com.bbjM.task.goal.application.api.requests.create_goal_request.CreateRecurringGoalRequest;
import com.bbjM.task.goal.application.api.requests.patch_goal_request.PatchGoalRequest;
import com.bbjM.task.goal.application.api.requests.patch_goal_request.PatchRecurringGoalRequest;
import com.bbjM.task.handler.APIException;
import com.bbjM.task.handler.ErrorCode;
import com.bbjM.task.shared.Auditable;
import com.bbjM.task.user.domain.Credential;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner")
    private Credential owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_goal")
    private BaseGoal parentGoal;

    @OneToMany(mappedBy = "parentGoal", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BaseGoal> children;

    @OneToOne(mappedBy = "goal", cascade = CascadeType.ALL, orphanRemoval = true)
    private RecurringGoal recurringGoal;

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
                goalType = GoalType.RECURRING;
                recurringGoal = new RecurringGoal((CreateRecurringGoalRequest) createGoalRequest, this);
                break;
            default:
                goalType = GoalType.SIMPLE;
                break;
        }
    }

    public void reschedule() {
        if (recurringGoal == null)
            throw new IllegalStateException("Esse objetivo não pode ser reprogramado!");

        schedule(LocalDateTime.now());
        recurringGoal.nextCycle();
    }

    public void schedule(LocalDateTime scheduledTime) {
        if (scheduledTime == null || scheduledTime.isBefore(LocalDateTime.now().minusMinutes(1)))
            throw new IllegalStateException("A data para programar objetivos deve ser válida!");

        this.status = Status.SCHEDULED;
    }

    @Override
    public void accomplish() {
        this.status = Status.COMPLETED;
    }

    public void updateGoalInfo(PatchGoalRequest patchGoalRequest) {
        if (patchGoalRequest.getGoalType() != this.goalType) {
            throw new APIException(ErrorCode.GOAL_TYPE_MISMATCH);
        }
        if (patchGoalRequest.getTitle() != null) this.title = patchGoalRequest.getTitle();
        if (patchGoalRequest.getDescription() != null) this.description = patchGoalRequest.getDescription();
        if (patchGoalRequest.getColor() != null) this.color = patchGoalRequest.getColor();

        if (patchGoalRequest instanceof PatchRecurringGoalRequest recurringGoalRequest) {
            if (recurringGoal == null) throw new APIException(ErrorCode.GOAL_NOT_RECURRING);
            this.recurringGoal.applyPatch(recurringGoalRequest);
        }

    }
}
