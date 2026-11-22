package com.bbjM.task.goal.domain;

import com.bbjM.task.goal.domain.requests.create_goal_request.*;
import com.bbjM.task.shared.Auditable;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Supplier;

import static java.util.stream.Collectors.toList;

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
    @Column(nullable = false)
    private Set<GoalType> goalTypes;

    @OneToMany(mappedBy = "goalHistory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GoalBehaviorSnapshot> goalBehaviorSnapshots;

    public GoalHistory(BaseGoal goal) {
        this.goalId = goal.getId();
        this.title = goal.getTitle();
        this.description = goal.getDescription();
        this.color = goal.getColor();
        this.status = goal.getStatus();
        this.level = goal.getLevel();
        this.goalTypes = goal.getGoalTypes();
        this.goalBehaviorSnapshots = new ArrayList<>();
        createGoalBehaviorSnapshots(goal);
    }

    private void createGoalBehaviorSnapshots(BaseGoal goal) {
        for (GoalType type : goal.getGoalTypes()) {
            var state = switch (type) {
                case RECURRING -> goal.getRecurringGoal().dumpState();
                case SCHEDULED -> goal.getScheduledGoal().dumpState();
                case DEADLINE  -> goal.getDeadlineGoal().dumpState();
                case REPEATING -> goal.getRepeatingGoal().dumpState();
                case STACKABLE -> goal.getStackableGoal().dumpState();
                case SIMPLE    -> new SimpleState();
            };

            goalBehaviorSnapshots.add(new GoalBehaviorSnapshot(this, state));
        }
    }

    public record SimpleState() {}
}
