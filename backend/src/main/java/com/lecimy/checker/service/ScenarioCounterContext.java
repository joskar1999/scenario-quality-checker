package com.lecimy.checker.service;

import com.lecimy.checker.model.Step;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScenarioCounterContext {

    @Setter
    private ScenarioCounter strategy;

    /**
     * Counting steps in scenario
     *
     * @param steps - steps provided by user
     * @return - number of steps in scenario
     */
    public int countSteps(List<Step> steps) {
        return strategy.countSteps(steps);
    }
}
