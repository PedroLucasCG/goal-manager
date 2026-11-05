package com.bbjM.task.goal.application.service;

import com.bbjM.task.goal.application.api.requests.CreateGoalRequest;
import com.bbjM.task.goal.application.api.responses.GoalResponse;

public interface GoalService {
    GoalResponse makeGoal(CreateGoalRequest createGoalRequest, String idCredential);
}
