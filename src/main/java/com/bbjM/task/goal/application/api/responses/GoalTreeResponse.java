package com.bbjM.task.goal.application.api.responses;

import com.bbjM.task.goal.domain.Goal;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
public class GoalTreeResponse {

    private final GoalResponse goal;
    private final List<GoalTreeResponse> children;

    private GoalTreeResponse(Goal goal, Map<UUID, List<Goal>> goalsByParentId) {
        this.goal = new GoalResponse(goal);
        this.children = new ArrayList<>();

        for (Goal child : goalsByParentId.getOrDefault(goal.getId(), List.of())) {
            this.children.add(new GoalTreeResponse(child, goalsByParentId));
        }
    }

    public static List<GoalTreeResponse> convertToTreeResponse(List<Goal> goals) {
        List<Goal> roots = new ArrayList<>();
        Map<UUID, List<Goal>> goalsByParentId = new HashMap<>();

        for (Goal goal : goals) {
            Goal parent = goal.getParentGoal();

            if (parent == null) {
                roots.add(goal);
            } else {
                goalsByParentId
                        .computeIfAbsent(parent.getId(), id -> new ArrayList<>())
                        .add(goal);
            }
        }

        List<GoalTreeResponse> trees = new ArrayList<>();
        for (Goal root : roots) {
            trees.add(new GoalTreeResponse(root, goalsByParentId));
        }
        return trees;
    }
}