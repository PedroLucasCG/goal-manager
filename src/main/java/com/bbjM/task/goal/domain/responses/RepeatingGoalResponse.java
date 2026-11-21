package com.bbjM.task.goal.domain.responses;

import com.bbjM.task.goal.domain.RepeatingGoal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RepeatingGoalResponse {
    private Long repetitionCounter;
    private Long repetitionLimit;

    public RepeatingGoalResponse(RepeatingGoal repeatingGoal) {
        this.repetitionLimit = repeatingGoal.getRepetitionLimit();
        this.repetitionCounter = repeatingGoal.getRepetitionCounter();
    }
}
