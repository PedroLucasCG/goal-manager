package com.bbjM.task.goal.application.api.responses;

import com.bbjM.task.goal.domain.Goal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.*;

@Getter
@NoArgsConstructor
public class GoalTreeResponse {
    private GoalResponse goal;
    private List<GoalTreeResponse> children;

    public GoalTreeResponse(List<Goal> goals) {
        var goalMap = groupGoalsByParentId(goals);
        this.children = new ArrayList<>();
        buildResponses(goalMap, null);
    }

    private GoalTreeResponse(Goal goal, HashMap<UUID, List<Goal>> goalMap) {
        this.goal = new GoalResponse(goal);
        this.children = new ArrayList<>();
        if (!goalMap.get(goal.getId()).isEmpty()) {
            for (Goal currGoal : goalMap.get(goal.getId())) {
                buildResponses(goalMap, currGoal.getId());
            }
        }
    }

    private void buildResponses(HashMap<UUID, List<Goal>> goalMap, UUID goalId) {
        List<Goal> currGoals
                = goalMap.getOrDefault(Objects.requireNonNullElse(goalId, this.goal.getId()), new ArrayList<>());

        for (Goal goal : currGoals) {
            children.add(new GoalTreeResponse(goal, goalMap));
        }
    }

    private HashMap<UUID, List<Goal>> groupGoalsByParentId(List<Goal> goals) {
        HashMap<UUID, List<Goal>> goalMap = new HashMap<>();
        for (Goal goal : goals) {
            if (goal.getParentGoal() == null) {
                this.goal = new GoalResponse(goal);
            } else {
                goalMap
                        .computeIfAbsent(goal.getParentGoal().getId(), key -> new ArrayList<>())
                        .add(goal);
            }
        }

        return goalMap;
    }
}
