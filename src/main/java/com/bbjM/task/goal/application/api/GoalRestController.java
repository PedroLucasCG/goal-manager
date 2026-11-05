package com.bbjM.task.goal.application.api;

import com.bbjM.task.goal.application.api.requests.CreateGoalRequest;
import com.bbjM.task.goal.application.api.responses.GoalResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequiredArgsConstructor
public class GoalRestController implements GoalAPI {
    @Override
    public GoalResponse makeGoal(CreateGoalRequest createGoalRequest) {
        log.info("[start] GoalRestController - makeGoal");

        log.debug("[finish] GoalRestController - makeGoal");
        return null;
    }
}
