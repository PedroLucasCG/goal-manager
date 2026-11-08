package com.bbjM.task.goal.application.service;

import com.bbjM.task.goal.application.api.responses.GoalTreeResponse;
import com.bbjM.task.goal.application.api.requests.CreateGoalRequest;
import com.bbjM.task.goal.application.api.responses.GoalResponse;
import org.springframework.data.domain.Page;

public interface GoalService {
    GoalResponse makeGoal(CreateGoalRequest createGoalRequest, String idCredential);

    GoalTreeResponse buildGoalTree(String email);
}
