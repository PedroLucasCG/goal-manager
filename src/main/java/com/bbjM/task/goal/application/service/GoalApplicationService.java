package com.bbjM.task.goal.application.service;

import com.bbjM.task.goal.application.api.requests.CreateGoalRequest;
import com.bbjM.task.goal.application.api.responses.GoalResponse;
import com.bbjM.task.goal.domain.Goal;
import com.bbjM.task.handler.APIException;
import com.bbjM.task.handler.ErrorCode;
import com.bbjM.task.user.application.service.AuthService;
import com.bbjM.task.user.domain.Credential;
import com.bbjM.task.user.infra.AuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@RequiredArgsConstructor
@Service
public class GoalApplicationService implements GoalService {
    private final AuthRepository authRepository;

    @Override
    public GoalResponse makeGoal(CreateGoalRequest createGoalRequest, String email) {
        log.info("[start] GoalApplicationService - makeGoal");
        Credential credential = authRepository.findUserByEmail(email)
                .orElseThrow(() -> new APIException(ErrorCode.CREDENTIAL_NOT_FOUND));;
        Goal goalFromRequest = new Goal(createGoalRequest, credential);

        log.debug("[finish] GoalApplicationService - makeGoal");
        return null;
    }
}
