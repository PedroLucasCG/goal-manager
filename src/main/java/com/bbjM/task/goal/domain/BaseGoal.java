package com.bbjM.task.goal.domain;

import com.bbjM.task.goal.domain.requests.create_goal_request.*;
import com.bbjM.task.goal.domain.requests.patch_goal_request.PatchGoalRequest;
import com.bbjM.task.goal.domain.requests.patch_goal_request.PatchRecurringGoalRequest;
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
    private List<GoalType> goalTypes;

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

    @OneToOne(mappedBy = "goal", cascade = CascadeType.ALL, orphanRemoval = true)
    private ScheduledGoal scheduledGoal;

    @OneToOne(mappedBy = "goal", cascade = CascadeType.ALL, orphanRemoval = true)
    private DeadlineGoal deadlineGoal;

    @OneToOne(mappedBy = "goal", cascade = CascadeType.ALL, orphanRemoval = true)
    private StackableGoal stackableGoal;

    @OneToOne(mappedBy = "goal", cascade = CascadeType.ALL, orphanRemoval = true)
    private RepeatingGoal repeatingGoal;

    public BaseGoal(CreateGoalRequest createGoalRequest, Credential owner, BaseGoal parentGoal) {
        this.title = createGoalRequest.getTitle();
        this.description = createGoalRequest.getDescription();
        this.color = createGoalRequest.getColor();
        this.parentGoal = parentGoal;
        this.owner = owner;
        this.status = Status.IN_PROGRESS;
        this.goalTypes = createGoalRequest.getGoalTypes();
        this.level = 0L;
        if(parentGoal != null) this.level = parentGoal.getLevel() + 1L;
        createGoalRequest.getBehaviors().forEach(this::createOnGoalType);
    }

    private void createOnGoalType(CreateGoalBehaviorRequest createGoalBehaviorRequest) {
        switch (createGoalBehaviorRequest.getGoalType()) {
            case RECURRING -> recurringGoal
                    = new RecurringGoal((CreateRecurringGoalRequest) createGoalBehaviorRequest, this);
            case SCHEDULED -> scheduledGoal
                    = new ScheduledGoal((CreateScheduledGoalRequest) createGoalBehaviorRequest, this);
            case DEADLINE -> deadlineGoal
                    = new DeadlineGoal((CreateDeadlineGoalRequest) createGoalBehaviorRequest, null);
            case REPEATING -> repeatingGoal
                    = new RepeatingGoal((CreateRepeatingGoalRequest) createGoalBehaviorRequest, this);
            case STACKABLE -> stackableGoal
                    = new StackableGoal((CreateStackableGoalRequest) createGoalBehaviorRequest, this);
            default -> throw new IllegalStateException("Tipo inválido");
        }
    }

    public void schedule(LocalDateTime scheduledTime) {
        if (scheduledTime == null || scheduledTime.isBefore(LocalDateTime.now().minusMinutes(1)))
            throw new IllegalStateException("A data para programar objetivos deve ser válida!");

        this.status = Status.SCHEDULED;
    }

    @Override
    public void accomplish() {
        if(status != Status.IN_PROGRESS) return;
        this.status = Status.COMPLETED;
    }

    public void updateGoalInfo(PatchGoalRequest patchGoalRequest) {
        if (patchGoalRequest.getTitle() != null) this.title = patchGoalRequest.getTitle();
        if (patchGoalRequest.getDescription() != null) this.description = patchGoalRequest.getDescription();
        if (patchGoalRequest.getColor() != null) this.color = patchGoalRequest.getColor();

        if (patchGoalRequest instanceof PatchRecurringGoalRequest recurringGoalRequest) {
            if (recurringGoal == null) throw new APIException(ErrorCode.GOAL_NOT_RECURRING);
            this.recurringGoal.applyPatch(recurringGoalRequest);
        }

    }
}
