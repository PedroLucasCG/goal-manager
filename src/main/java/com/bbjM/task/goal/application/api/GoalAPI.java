package com.bbjM.task.goal.application.api;

import com.bbjM.task.goal.application.api.requests.CreateGoalRequest;
import com.bbjM.task.goal.application.api.requests.GoalFilterRequest;
import com.bbjM.task.goal.application.api.requests.PatchGoalRequest;
import com.bbjM.task.goal.application.api.responses.GoalResponse;
import com.bbjM.task.goal.application.api.responses.GoalTreeResponse;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/goal")
public interface GoalAPI {
    @PostMapping("/{email}")
    GoalResponse makeGoal(@Valid @RequestBody CreateGoalRequest createGoalRequest, @PathVariable String email);

    @GetMapping("/tree/{email}")
    List<GoalTreeResponse> showGoalTree(@Valid @PathVariable String email,
                                        @ParameterObject @ModelAttribute GoalFilterRequest goalFilterRequest);

    @PatchMapping("/{email}/{goalId}")
    GoalResponse patchGoalInfo(
            @Valid @RequestBody PatchGoalRequest patchGoalRequest, @PathVariable String email, @PathVariable UUID goalId);
}
