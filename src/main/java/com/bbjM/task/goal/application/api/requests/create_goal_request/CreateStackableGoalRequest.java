package com.bbjM.task.goal.application.api.requests.create_goal_request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateStackableGoalRequest extends CreateGoalRequest{
    private Long quantity;
}
