package com.bbjM.task.goal.application.api.responses;

import com.bbjM.task.goal.domain.BaseGoal;
import lombok.Getter;

import java.util.*;

@Getter
public class GoalTreeResponse {

    private final GoalResponse goal;
    private final List<GoalTreeResponse> children;

    private GoalTreeResponse(BaseGoal goal, Map<UUID, List<BaseGoal>> goalsByParentId) {
        this.goal = new GoalResponse(goal);
        this.children = new ArrayList<>();

        for (BaseGoal child : goalsByParentId.getOrDefault(goal.getId(), List.of())) {
            this.children.add(new GoalTreeResponse(child, goalsByParentId));
        }
    }

    public static List<GoalTreeResponse> convertToTreeResponse(List<BaseGoal> goals, Long initLevel) {
        List<BaseGoal> roots = new ArrayList<>();
        Map<UUID, List<BaseGoal>> goalsByParentId = new HashMap<>();

        for (BaseGoal goal : goals) {
            BaseGoal parent = goal.getParentGoal();

            if (Objects.equals(goal.getLevel(), initLevel)) {
                roots.add(goal);
            } else {
                goalsByParentId
                        .computeIfAbsent(parent.getId(), id -> new ArrayList<>())
                        .add(goal);
            }
        }

        List<GoalTreeResponse> trees = new ArrayList<>();
        for (BaseGoal root : roots) {
            trees.add(new GoalTreeResponse(root, goalsByParentId));
        }
        return trees;
    }
}