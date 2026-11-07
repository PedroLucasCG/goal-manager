package com.bbjM.task.goal.application.service;

import com.bbjM.task.goal.application.api.requests.CreateGoalRequest;
import com.bbjM.task.goal.application.api.responses.GoalResponse;
import com.bbjM.task.goal.domain.Goal;
import com.bbjM.task.goal.infra.GoalRepository;
import com.bbjM.task.handler.APIException;
import com.bbjM.task.handler.ErrorCode;
import com.bbjM.task.user.application.service.AuthService;
import com.bbjM.task.user.domain.Credential;
import com.bbjM.task.user.infra.AuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
@Service
public class GoalApplicationService implements GoalService {
    private final AuthRepository authRepository;
    private final GoalRepository goalRepository;

    @Override
    public GoalResponse makeGoal(CreateGoalRequest createGoalRequest, String email) {
        log.info("[start] GoalApplicationService - makeGoal");
        Credential owner = authRepository.findUserByEmail(email)
                .orElseThrow(() -> new APIException(ErrorCode.CREDENTIAL_NOT_FOUND));
        Goal parentGoal = null;
        if (createGoalRequest.getParentGoal() != null) {
            parentGoal = getGoalById(createGoalRequest.getParentGoal(), owner)
                    .orElseThrow(() -> new APIException(ErrorCode.PARENT_GOAL_NOT_FOUND));
        }
        Goal goalFromRequest = new Goal(createGoalRequest, owner, parentGoal);
        Goal goal = goalRepository.saveGoal(goalFromRequest);
        GoalResponse goalResponse = new GoalResponse(goal);
        log.debug("[finish] GoalApplicationService - makeGoal");
        return goalResponse;
    }

    private Optional<Goal> getGoalById(UUID goalId, Credential owner) {
        log.info("[start] GoalApplicationService - getGoalById");
        Optional<Goal> goalFromDB = goalRepository.findGoalById(goalId, owner);
        log.debug("[finish] GoalApplicationService - getGoalById");
        return goalFromDB;
    }
}
