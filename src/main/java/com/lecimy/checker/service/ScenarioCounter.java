package com.lecimy.checker.service;

import com.lecimy.checker.model.Step;

import java.util.List;

public interface ScenarioCounter {

    /**
     * This method should count steps in provided list
     *
     * @param steps - list of steps provided by user
     * @return - number of steps
     */
    int countSteps(List<Step> steps);
}
