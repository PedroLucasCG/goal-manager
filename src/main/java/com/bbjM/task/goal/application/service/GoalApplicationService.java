package com.bbjM.task.goal.application.service;

import com.bbjM.task.goal.domain.requests.GoalFilterRequest;
import com.bbjM.task.goal.domain.requests.patch_goal_request.PatchGoalRequest;
import com.bbjM.task.goal.domain.responses.GoalTreeResponse;
import com.bbjM.task.goal.domain.requests.create_goal_request.CreateGoalRequest;
import com.bbjM.task.goal.domain.responses.GoalResponse;
import com.bbjM.task.goal.domain.BaseGoal;
import com.bbjM.task.goal.domain.Goal;
import com.bbjM.task.goal.infra.GoalRepository;
import com.bbjM.task.handler.APIException;
import com.bbjM.task.handler.ErrorCode;
import com.bbjM.task.user.domain.Credential;
import com.bbjM.task.user.infra.AuthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
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
        BaseGoal parentGoal = null;
        if (createGoalRequest.getParentGoal() != null) {
            parentGoal = goalRepository.findGoalById(createGoalRequest.getParentGoal(), owner)
                    .orElseThrow(() -> new APIException(ErrorCode.PARENT_GOAL_NOT_FOUND));
        }
        BaseGoal goalFromRequest = new BaseGoal(createGoalRequest, owner, parentGoal);
        BaseGoal goal = goalRepository.saveGoal(goalFromRequest);
        GoalResponse goalResponse = new GoalResponse(goal);
        log.debug("[finish] GoalApplicationService - makeGoal");
        return goalResponse;
    }

    @Override
    public List<GoalTreeResponse> buildGoalTree(String email, GoalFilterRequest goalFilterRequest) {
        log.info("[start] GoalApplicationService - buildGoalTree");
        List<BaseGoal> rootGoals = goalRepository.findRootGoalsByUserEmail(email, goalFilterRequest);
        List<GoalTreeResponse> goalTrees
                = GoalTreeResponse.convertToTreeResponse(rootGoals, goalFilterRequest.getStartLevel());
        log.debug("[finish] GoalApplicationService - buildGoalTree");
        return goalTrees;
    }

    @Override
    public GoalResponse patchGoalInfo(PatchGoalRequest patchGoalRequest, String email, UUID goalId) {
        log.info("[start] GoalApplicationService - patchGoalInfo");
        BaseGoal goalToUpdate = getGoalById(goalId, email);
        goalToUpdate.updateGoalInfo(patchGoalRequest);
        BaseGoal updatedGoal = goalRepository.saveGoal(goalToUpdate);
        GoalResponse goalResponse = new GoalResponse(updatedGoal);
        log.debug("[finish] GoalApplicationService - patchGoalInfo");
        return goalResponse;
    }

    @Override
    public BaseGoal deleteGoalTree(String email, UUID goalId) {
        log.info("[start] GoalApplicationService - deleteGoalTree");
        BaseGoal goalToDelete = getGoalById(goalId, email);
        goalRepository.deleteGoal(goalToDelete);
        log.debug("[finish] GoalApplicationService - deleteGoalTree");
        return goalToDelete;
    }

    @Override
    public BaseGoal accomplishGoal(String email, UUID goalId) {
        log.info("[start] GoalApplicationService - accomplishGoal");
        BaseGoal goalToAccomplish = getGoalById(goalId, email);
        Goal goalDecorated = Goal.decorateGoalByType(goalToAccomplish);
        goalDecorated.accomplish();
        BaseGoal accomplishedGoal = goalRepository.saveGoal(goalToAccomplish);
        log.debug("[finish] GoalApplicationService - accomplishGoal");
        return accomplishedGoal;
    }

    private BaseGoal getGoalById(UUID goalId, String email) {
        log.info("[start] GoalApplicationService - getGoalById");
        Credential owner = authRepository.findUserByEmail(email)
                .orElseThrow(() -> new APIException(ErrorCode.CREDENTIAL_NOT_FOUND));
        BaseGoal goal = goalRepository.findGoalById(goalId, owner)
                .orElseThrow(() -> new APIException(ErrorCode.GOAL_NOT_FOUND));
        log.debug("[finish] GoalApplicationService - getGoalById");
        return goal;
    }
}
