package com.bbjM.task.goal.application.service;

import com.bbjM.task.goal.application.api.requests.CreateGoalRequest;
import com.bbjM.task.goal.application.api.requests.GoalFilterRequest;
import com.bbjM.task.goal.application.api.requests.PatchGoalRequest;
import com.bbjM.task.goal.application.api.responses.GoalResponse;
import com.bbjM.task.goal.application.api.responses.GoalTreeResponse;

import java.util.List;

public interface GoalService {
    GoalResponse makeGoal(CreateGoalRequest createGoalRequest, String idCredential);

    List<GoalTreeResponse> buildGoalTree(String email, GoalFilterRequest goalFilterRequest);

    GoalResponse patchGoalInfo(PatchGoalRequest patchGoalRequest);
}
