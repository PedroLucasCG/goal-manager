package com.bbjM.task.goal.application.service;

import com.bbjM.task.goal.application.api.requests.create_goal_request.CreateGoalRequest;
import com.bbjM.task.goal.application.api.requests.GoalFilterRequest;
import com.bbjM.task.goal.application.api.requests.patch_goal_request.PatchGoalRequest;
import com.bbjM.task.goal.application.api.responses.GoalResponse;
import com.bbjM.task.goal.application.api.responses.GoalTreeResponse;
import com.bbjM.task.goal.domain.BaseGoal;

import java.util.List;
import java.util.UUID;

public interface GoalService {
    GoalResponse makeGoal(CreateGoalRequest createGoalRequest, String idCredential);

    List<GoalTreeResponse> buildGoalTree(String email, GoalFilterRequest goalFilterRequest);

    GoalResponse patchGoalInfo(PatchGoalRequest patchGoalRequest, String email, UUID goalId);

    BaseGoal deleteGoalTree(String email, UUID goalId);

    BaseGoal accomplishGoal(String email, UUID goalId);
}
