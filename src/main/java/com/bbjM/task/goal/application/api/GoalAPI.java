package com.bbjM.task.goal.application.api;

import com.bbjM.task.goal.application.api.requests.CreateGoalRequest;
import com.bbjM.task.goal.application.api.requests.GoalFilterRequest;
import com.bbjM.task.goal.application.api.requests.PatchGoalRequest;
import com.bbjM.task.goal.application.api.responses.GoalResponse;
import com.bbjM.task.goal.application.api.responses.GoalTreeResponse;
import jakarta.validation.Valid;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/goal/{email}")
public interface GoalAPI {
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    GoalResponse makeGoal(@Valid @RequestBody CreateGoalRequest createGoalRequest, @PathVariable String email);

    @GetMapping("/tree")
    @ResponseStatus(HttpStatus.OK)
    List<GoalTreeResponse> showGoalTree(@Valid @PathVariable String email,
                                        @ParameterObject @ModelAttribute GoalFilterRequest goalFilterRequest);

    @PatchMapping("/{goalId}")
    @ResponseStatus(HttpStatus.OK)
    GoalResponse patchGoalInfo(
            @Valid @RequestBody PatchGoalRequest patchGoalRequest,
            @PathVariable String email, @PathVariable UUID goalId);

    @DeleteMapping("/{goalId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteGoalTree(@Valid @PathVariable String email, @PathVariable UUID goalId);
}
