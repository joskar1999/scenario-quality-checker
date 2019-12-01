package com.lecimy.checker.service;

import com.lecimy.checker.model.Step;

import java.util.List;

public interface ScenarioCounter {

    int countSteps(List<Step> steps);
}
