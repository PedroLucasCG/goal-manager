package com.bbjM.task.goal.application.api;

import com.bbjM.task.goal.application.api.requests.CreateGoalRequest;
import com.bbjM.task.goal.application.api.responses.GoalResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/goal")
public interface GoalAPI {
    @PostMapping("/{email}")
    GoalResponse makeGoal(@Valid @RequestBody CreateGoalRequest createGoalRequest, @PathVariable String email);
}
