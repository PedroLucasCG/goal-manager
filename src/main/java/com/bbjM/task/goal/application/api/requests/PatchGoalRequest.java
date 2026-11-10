package com.bbjM.task.goal.application.api.requests;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PatchGoalRequest {
    @NotBlank(message = "O objetivo precisa de um nome!")
    private String title;
    @NotBlank(message = "O objetivo precisa de uma descrição!")
    private String description;
    private String color;
}
