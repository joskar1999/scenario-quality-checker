package com.lecimy.checker.service;

import com.lecimy.checker.model.Step;

import java.util.List;

public class ScenarioStepsCounter implements ScenarioCounter {

    /**
     * This method counts all steps in provided scenario
     *
     * @param steps - list of steps provided by user
     * @return - number of steps in scenario
     */
    @Override
    public int countSteps(List<Step> steps) {
        if (steps.isEmpty()) {
            return 0;
        } else {
            int nodes = 0;
            for (Step s : steps) {
                nodes += countSteps(s.getSubSteps()) + 1;
            }
            return nodes;
        }
    }
}
