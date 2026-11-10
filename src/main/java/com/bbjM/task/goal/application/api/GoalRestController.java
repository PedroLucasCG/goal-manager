package com.bbjM.task.goal.application.api;

import com.bbjM.task.goal.application.api.requests.CreateGoalRequest;
import com.bbjM.task.goal.application.api.requests.GoalFilterRequest;
import com.bbjM.task.goal.application.api.requests.PatchGoalRequest;
import com.bbjM.task.goal.application.api.responses.GoalResponse;
import com.bbjM.task.goal.application.api.responses.GoalTreeResponse;
import com.bbjM.task.goal.application.service.GoalService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Log4j2
@RestController
@RequiredArgsConstructor
public class GoalRestController implements GoalAPI {
    private final GoalService goalService;

    @Override
    public GoalResponse makeGoal(CreateGoalRequest createGoalRequest, String email) {
        log.info("[start] GoalRestController - makeGoal");
        GoalResponse goalResponse = goalService.makeGoal(createGoalRequest, email);
        log.debug("[finish] GoalRestController - makeGoal");
        return goalResponse;
    }

    @Override
    public List<GoalTreeResponse> showGoalTree(String email, GoalFilterRequest goalFilterRequest) {
        log.info("[start] GoalRestController - showGoalTree");
        List<GoalTreeResponse> goalResponses
                = goalService.buildGoalTree(email, goalFilterRequest);
        log.debug("[finish] GoalRestController - showGoalTree");
        return goalResponses;
    }

    @Override
    public GoalResponse patchGoalInfo(PatchGoalRequest patchGoalRequest, String email, UUID goalId) {
        log.info("[start] GoalRestController - patchGoalInfo");
        GoalResponse goalResponse = goalService.patchGoalInfo(patchGoalRequest, email, goalId);
        log.debug("[finish] GoalRestController - patchGoalInfo");
        return goalResponse;
    }
}
