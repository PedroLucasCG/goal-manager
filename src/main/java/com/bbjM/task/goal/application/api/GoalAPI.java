package com.bbjM.task.goal.application.api;

import com.bbjM.task.goal.application.api.requests.CreateGoalRequest;
import com.bbjM.task.goal.application.api.responses.GoalResponse;
import com.bbjM.task.goal.application.api.responses.GoalTreeResponse;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/goal")
public interface GoalAPI {
    @PostMapping("/{email}")
    GoalResponse makeGoal(@Valid @RequestBody CreateGoalRequest createGoalRequest, @PathVariable String email);

    @GetMapping("/tree/{email}")
    List<GoalTreeResponse> showGoalTree(@Valid @PathVariable String email);
}
