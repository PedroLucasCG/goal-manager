package com.bbjM.task.goal.domain;

import com.bbjM.task.goal.domain.requests.create_goal_request.CreateGoalBehaviorRequest;
import com.bbjM.task.goal.domain.requests.create_goal_request.*;
import com.bbjM.task.goal.domain.requests.patch_goal_request.*;
import com.bbjM.task.shared.Auditable;
import com.bbjM.task.user.domain.Credential;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;

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
        createGoalRequest.getBehaviors().forEach(this::setGoalBehaviors);
    }

    public void schedule(LocalDateTime scheduledTime) {
        if (scheduledTime == null || scheduledTime.isBefore(LocalDateTime.now().minusMinutes(1)))
            throw new IllegalStateException("A data para programar objetivos deve ser válida!");

        this.status = Status.SCHEDULED;
    }

    public void updateGoalInfo(PatchGoalRequest patchGoalRequest) {
        if (patchGoalRequest.getTitle() != null) this.title = patchGoalRequest.getTitle();
        if (patchGoalRequest.getDescription() != null) this.description = patchGoalRequest.getDescription();
        if (patchGoalRequest.getColor() != null) this.color = patchGoalRequest.getColor();

        patchGoalRequest.getBehaviors().forEach(this::patchGoalBehaviors);
    }

    @Override
    public void accomplish() {
        if(status != Status.IN_PROGRESS)
            throw new IllegalStateException("Não é possível mudar o status de um goal sem ser IN_PROGRESS");
        this.status = Status.COMPLETED;
    }

    private void setGoalBehaviors(CreateGoalBehaviorRequest createGoalBehaviorRequest) {
        switch (createGoalBehaviorRequest.getGoalType()) {
            case RECURRING -> recurringGoal
                    = new RecurringGoal((RecurringCreateGoalRequest) createGoalBehaviorRequest, this);
            case SCHEDULED -> scheduledGoal
                    = new ScheduledGoal((ScheduledCreateGoalRequest) createGoalBehaviorRequest, this);
            case DEADLINE -> deadlineGoal
                    = new DeadlineGoal((DeadlineCreateGoalRequest) createGoalBehaviorRequest, this);
            case REPEATING -> repeatingGoal
                    = new RepeatingGoal((RepeatingCreateGoalRequest) createGoalBehaviorRequest, this);
            case STACKABLE -> stackableGoal
                    = new StackableGoal((StackableCreateGoalRequest) createGoalBehaviorRequest, this);
            case SIMPLE -> List.of();
            default -> throw new IllegalStateException
                    ("Tipo inválido" + createGoalBehaviorRequest.getGoalType().toString());
        }
    }

    private void patchGoalBehaviors(PatchGoalBehaviorRequest patchGoalBehaviorRequest) {
        switch (patchGoalBehaviorRequest.getGoalType()) {
            case RECURRING -> createOrUpdateBehavior(
                    recurringGoal,
                    (RecurringPatchGoalRequest) patchGoalBehaviorRequest,
                    RecurringGoal::fromPatch,
                    RecurringGoal::applyPatch);
            case SCHEDULED -> createOrUpdateBehavior(
                    scheduledGoal,
                    (ScheduledPatchGoalRequest) patchGoalBehaviorRequest,
                    ScheduledGoal::fromPatch,
                    ScheduledGoal::applyPatch
            );
            case DEADLINE -> createOrUpdateBehavior(
                    deadlineGoal,
                    (DeadlinePatchGoalRequest) patchGoalBehaviorRequest,
                    DeadlineGoal::fromPatch,
                    DeadlineGoal::applyPatch
            );
            case REPEATING -> createOrUpdateBehavior(
                    repeatingGoal,
                    (RepeatingPatchGoalRequest) patchGoalBehaviorRequest,
                    RepeatingGoal::fromPatch,
                    RepeatingGoal::applyPatch
            );
            case STACKABLE -> createOrUpdateBehavior(
                    stackableGoal,
                    (StackablePatchGoalRequest) patchGoalBehaviorRequest,
                    StackableGoal::fromPatch,
                    StackableGoal::applyPatch
            );
            default -> throw new IllegalStateException("Tipo inválido");
        };
    }

    private <B, P extends PatchGoalBehaviorRequest> void createOrUpdateBehavior(
            B behavior,
            P patch,
            Function<P, CreateGoalBehaviorRequest> create,
            BiConsumer<B, P> update
    ) {
        if(behavior == null) {
            CreateGoalBehaviorRequest goalBehavior = create.apply(patch);
            setGoalBehaviors(goalBehavior);
            return;
        }

        update.accept(behavior, patch);
    }
}
