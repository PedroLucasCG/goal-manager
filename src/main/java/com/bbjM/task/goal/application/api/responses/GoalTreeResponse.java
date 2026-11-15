package com.bbjM.task.goal.application.api.responses;

import com.bbjM.task.goal.domain.BaseGoal;
import com.bbjM.task.goal.domain.Status;
import lombok.Getter;

import java.util.*;

@Getter
public class GoalTreeResponse {

    private final GoalResponse goal;
    private final List<GoalTreeResponse> children;
    private Long progress = 0L;

    private GoalTreeResponse(BaseGoal goal, Map<UUID, List<BaseGoal>> goalsByParentId) {
        this.goal = new GoalResponse(goal);
        this.children = new ArrayList<>();

        for (BaseGoal child : goalsByParentId.getOrDefault(goal.getId(), List.of())) {
            this.children.add(new GoalTreeResponse(child, goalsByParentId));
        }

        updateProgress(goalsByParentId.getOrDefault(goal.getId(), List.of()));
    }

    private void updateProgress(List<BaseGoal> goals) {
        int total = goals.size();
        if (total == 0) return;
        long completed = goals.stream()
                .filter(g -> g.getStatus() == Status.COMPLETED)
                .count();
        this.progress = Math.round((double) (completed * 10000L) / total) / 100L;
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