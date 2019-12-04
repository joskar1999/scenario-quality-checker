package com.lecimy.checker.service;

import com.lecimy.checker.model.Scenario;
import com.lecimy.checker.model.Step;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScenarioFlattingService {

    /**
     * This method will flat scenario to desired level
     *
     * @param scenario - scenario provided by the user
     * @param level    - level on which other sub-steps should be cut
     * @return - flattened scenario
     */
    public List<Step> flatScenario(Scenario scenario, int level) {
        Scenario toFlat = new Scenario(scenario);
        flatScenario(toFlat.getSteps(), level, 1);
        return toFlat.getSteps();
    }

    private void flatScenario(List<Step> steps, int level, int currentLevel) {
        steps.forEach(step -> {
            if (level == currentLevel) {
                step.setSubSteps(null);
            } else {
                flatScenario(step.getSubSteps(), level, currentLevel + 1);
            }
        });
    }
}
