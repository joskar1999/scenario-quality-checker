package com.lecimy.checker.service;

import com.lecimy.checker.model.Step;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScenarioCounterContext {

    @Setter
    private ScenarioCounter strategy;

    public int countSteps(List<Step> scenario) {
        return strategy.countSteps(scenario);
    }
}
