package com.bbjM.task.goal.application.api;

import com.bbjM.task.goal.domain.requests.create_goal_request.CreateGoalRequest;
import com.bbjM.task.goal.domain.requests.GoalFilterRequest;
import com.bbjM.task.goal.domain.requests.patch_goal_request.PatchGoalRequest;
import com.bbjM.task.goal.domain.responses.GoalResponse;
import com.bbjM.task.goal.domain.responses.GoalTreeResponse;
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

    @PatchMapping("/accomplish/{goalId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void accomplishGoal(@Valid @PathVariable String email, @PathVariable UUID goalId);

}
