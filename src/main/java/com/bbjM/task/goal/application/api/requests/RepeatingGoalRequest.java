package com.bbjM.task.goal.application.api.requests;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RepeatingGoalRequest {
    private Long repeationCounter;
    private Long repeationLimit;
}
